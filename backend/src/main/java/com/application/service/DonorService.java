package com.application.service;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.application.filter.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.model.Donor;
import com.application.model.Requesting;
import com.application.repository.DonorRepository;
import com.application.repository.RequestingBloodRepository;
@Slf4j
@Service
public class DonorService
{
    private static final Logger logger = LoggerFactory.getLogger(DonorService.class);

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private RequestingBloodRepository requestingBloodRepository;

    public Donor saveDonor(Donor donor)
    {
        logger.info("[INFO]: inside saveDonor ");

        return donorRepository.save(donor);
    }

    public Donor saveUserAsDonor(Donor donor)
    {
        logger.info("[INFO]: inside saveUserAsDonor ");

        return donorRepository.save(donor);
    }

    public Requesting saveBloodRequest(Requesting request)
    {
        logger.info("[INFO]: inside saveBloodRequest ");

        return requestingBloodRepository.save(request);
    }

    public Donor fetchDonorByBloodGroup(String bloodGroup)
    {
        logger.info("[INFO]: inside fetchDonorByBloodGroup ");

        return donorRepository.findByBloodGroup(bloodGroup);
    }

    public void updateStatus(String email)
    {
        logger.info("[INFO]: inside updateStatus ");

        requestingBloodRepository.updateStatus(email);
        System.out.println("Updated");
    }

    public void rejectStatus(String email)
    {
        logger.info("[INFO]: inside rejectStatus ");

        requestingBloodRepository.rejectStatus(email);
    }

    public Donor fetchDonorByGender(String gender)
    {
        logger.info("[INFO]: inside fetchDonorByGender ");

        return donorRepository.findByGender(gender);
    }

    public List<Donor> getAllDonors()
    {
        logger.info("[INFO]: inside getAllDonors ");

        return (List<Donor>)donorRepository.findAll();
    }

    public List<Requesting> getRequestHistory()
    {
        logger.info("[INFO]: inside getRequestHistory ");

        return (List<Requesting>)requestingBloodRepository.findAll();
    }

    public List<Requesting> getRequestHistoryByEmail(String email)
    {
        logger.info("[INFO]: inside getRequestHistoryByEmail ");

        return (List<Requesting>)requestingBloodRepository.findByEmail(email);
    }

    public List<Donor> getBloodDetails()
    {
        logger.info("[INFO]: inside getBloodDetails ");

        return (List<Donor>)donorRepository.findBloodDetails();
    }

    public void checkforOldBloodSamples(List<Donor> donors)
    {
        logger.info("[INFO]: inside checkforOldBloodSamples ");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String todayDate = formatter.format(date);
        for(Donor donorlist:donors)
        {
            String donationDate = donorlist.getDate();
            long days = findDifference(donationDate, todayDate);
            if(days > 90)
            {
                String userName = donorlist.getName();
                donorRepository.deleteByUsername(userName);
            }
        }
    }
    static long findDifference(String donationDate,String todayDate)
    {
        logger.info("[INFO]: inside findDifference ");

        long daysDifference = 0;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(donationDate);
            Date date2 = sdf.parse(todayDate);
            long timeDifference = date2.getTime() - date1.getTime();
            daysDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
            System.out.println("The Blood sample is "+daysDifference + " days older.");
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
        return daysDifference;
    }
}