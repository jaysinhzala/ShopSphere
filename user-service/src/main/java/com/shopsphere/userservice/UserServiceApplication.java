package com.shopsphere.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        // Print environment variable to check if it's loaded
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
