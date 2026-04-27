package com.shopsphere.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    // JWT token sent back to client after successful login
    private String token;

    // User's role - so frontend knows what they can access
    private String role;

    // Message to tell client if login was successful
    private String message;
}
