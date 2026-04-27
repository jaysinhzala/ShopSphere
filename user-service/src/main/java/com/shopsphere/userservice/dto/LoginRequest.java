package com.shopsphere.userservice.dto;

import lombok.Data;

@Data
public class LoginRequest {
    // User's email address
    private String email;

    // User's password
    private String password;
}
