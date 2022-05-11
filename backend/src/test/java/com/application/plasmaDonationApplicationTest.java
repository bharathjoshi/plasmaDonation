package com.application;

import com.application.model.User;
import com.application.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
public class plasmaDonationApplicationTest {
    @Test
    void contextLoads() {
    }
    private RegistrationService registrationService;

    @Autowired
    public plasmaDonationApplicationTest(RegistrationService registrationService){
        this.registrationService = registrationService;
    }
    @Test
    public void positiveUserLogin1(){
        User user = new User("forTest@gmail.com","testUser","00", "ab","M",18,"password");
        registrationService.saveUser(user);
        User test = registrationService.fetchUserByEmail(user.getEmail());
        assertEquals(user.getUsername(),test.getUsername());
    }

    @Test
    public void negativeUserLogin1(){
        User user = new User("forTest@gmail.com","testUser","00", "ab","M",18,"password");
        User test1 = registrationService.saveUser(user);
        User test = registrationService.fetchUserByEmail(user.getEmail());
        user.setUsername("Wrong name");
        assertNotEquals(user.getUsername(),test1.getUsername());
    }

    @Test
    public void positiveUserLogin2(){
        User user = new User("forTest2@gmail.com","testUser2","00", "ab","M",18,"password");
        registrationService.saveUser(user);
        User test = registrationService.fetchUserByEmail(user.getEmail());
        assertEquals(user.getUsername(),test.getUsername());
    }

    @Test
    public void negativeUserLogin2(){
        User user = new User("forTest2@gmail.com","testUser2","00", "ab","M",18,"password");
        User test1 = registrationService.saveUser(user);
        User test = registrationService.fetchUserByEmail(user.getEmail());
        user.setUsername("Wrong name");
        assertNotEquals(user.getUsername(),test1.getUsername());
    }




}
