package com.application.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.application.model.User;
import com.application.repository.RegistrationRepository;

@Slf4j
@Service
public class RegistrationService implements UserDetailsService
{
    @Autowired
    private RegistrationRepository registrationRepo;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException
    {
        logger.info("[INFO]: inside loadUserByEmail ");

        User user = registrationRepo.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public User saveUser(User user)
    {
        logger.info("[INFO]: inside saveUser ");

        return registrationRepo.save(user);
    }
    public User updateUserProfile(User user)
    {
        logger.info("[INFO]: inside updateUserProfile ");

        return registrationRepo.save(user);
    }

    public User fetchUserByEmail(String email)
    {
        logger.info("[INFO]: inside fetchUserByEmail ");

        return registrationRepo.findByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String email, String password)
    {
        logger.info("[INFO]: inside fetchUserByEmailAndPassword ");

        return registrationRepo.findByEmailAndPassword(email, password);
    }

    public List<User> getAllUsers()
    {
        logger.info("[INFO]: inside getAllUsers ");

        return (List<User>)registrationRepo.findAll();
    }

    public List<User> fetchProfileByEmail(String email)
    {
        logger.info("[INFO]: inside fetchProfileByEmail ");

        return (List<User>)registrationRepo.findProfileByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        logger.info("[INFO]: inside loadUserByUsername ");

        User user = registrationRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}