package com.application.config;

import javax.servlet.Filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.application.filter.JwtFilter;
import com.application.service.RegistrationService;

@SuppressWarnings("unused")
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private JwtFilter jwtFilter;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        logger.info("[INFO]: inside configure");
        auth.userDetailsService(registrationService);
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        logger.info("[INFO]: inside passwordEncoder");

        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        logger.info("[INFO]: inside authenticationManagerBean");

        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) {
        // Do nothing, this is just overriding the default behavior in WebSecurityConfigurerAdapter
        
    }

    
    
    
}

