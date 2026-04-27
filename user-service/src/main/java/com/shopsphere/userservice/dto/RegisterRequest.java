package com.shopsphere.userservice.dto;

import lombok.Data;
import com.shopsphere.userservice.model.Role;

@Data
public class RegisterRequest {
    // User's full name
    private String name;

    // User's email address
    private String email;

    // User's password - will be hashed before saving
    private String password;

    // User's role - CUSTOMER or SELLER
    private Role role;
}
