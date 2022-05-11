package com.application.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.application.model.AuthRequest;
import com.application.model.User;
import com.application.service.RegistrationService;
import com.application.util.JwtUtils;

@Slf4j
@RestController
public class RegistrationController
{
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registerService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcomeMessage()
    {
        return "Welcome to Blood Bank Management system !!!";
    }

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception
    {
        try
        {

            logger.info("[INFO]: inside generateToken ");
            List<User> users = registerService.getAllUsers();
            String currentEmail = "";
            for(User obj:users)
            {
                if(obj.getEmail().equalsIgnoreCase(authRequest.getEmail()))
                {
                    currentEmail = obj.getUsername();
                }
            }
            logger.info("[INFO]: Authenticating ");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentEmail, authRequest.getPassword()));
        }
        catch (Exception ex)
        {
            logger.error("[ERROR] : Invalid Username/password");
            throw new Exception("Invalid Username/password");
        }
        return new ResponseEntity<String>(jwtUtil.generateToken(authRequest.getEmail()), HttpStatus.OK);
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public User registerUser(@RequestBody User user) throws Exception
    {
        logger.info("[INFO]:inside registerUser ");

        String currEmail = user.getEmail();
        if(currEmail != null || !"".equals(currEmail))
        {
            User userObj = registerService.fetchUserByEmail(currEmail);
            if(userObj != null)
            {
                logger.error("[ERROR] :"+ "User with "+currEmail+" already exists");
                throw new Exception("User with "+currEmail+" already exists !!!");
            }
        }
        User userObj = null;
        userObj = registerService.saveUser(user);
        logger.info("[INFO]:User saved");

        return userObj;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public User loginUser(@RequestBody User user) throws Exception
    {

        logger.info("[INFO]:inside loginUser ");

        String currEmail = user.getEmail();
        String currPassword = user.getPassword();

        User userObj = null;
        if(currEmail != null && currPassword != null)
        {
            userObj = registerService.fetchUserByEmailAndPassword(currEmail, currPassword);
        }
        if(userObj == null)
        {
            logger.error("[ERROR] :"+"User does not exists!!! Please enter valid credentials...");

            throw new Exception("User does not exists!!! Please enter valid credentials...");
        }
        logger.info("[INFO]:Login Successful");

        return userObj;
    }

    @PutMapping("/updateuser")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> updateUserProfile(@RequestBody User user) throws Exception
    {
        logger.info("[INFO]:inside updateUserProfile");

        registerService.updateUserProfile(user);
        logger.info("[INFO]:UpdatedUserProfile");

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}