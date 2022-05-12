package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.sql.DataSource;


@SpringBootApplication
public class plasmaDonationApplication {
    public static void main(String[] args) {
        SpringApplication.run(plasmaDonationApplication.class, args);
    }

    // @Bean
    // public DataSource getDataSource() {
    //     DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    //     dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
    //     String url = System.getenv("DATABASE_HOST");
    //     System.out.println(url);
    //     if (url != null) {
    //         dataSourceBuilder.url("jdbc:mysql://plasmasqldb:3306/PlasmaDonation?useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&failOverReadOnly=false&maxReconnects=10");
    //         dataSourceBuilder.username("root");
    //         dataSourceBuilder.password("lana3I2BQC?");

    //     } else {
    //         dataSourceBuilder.url("jdbc:mysql://localhost:3306/PlasmaDonation");
    //         dataSourceBuilder.username("aryavrdhn");
    //         dataSourceBuilder.password("lana3I2BQC?");
    //     }
        

    //     return dataSourceBuilder.build();
    // }
}


 