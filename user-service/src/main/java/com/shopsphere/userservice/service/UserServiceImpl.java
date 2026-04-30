package com.shopsphere.userservice.service;

import com.shopsphere.userservice.config.JwtService;
import com.shopsphere.userservice.dto.AuthResponse;
import com.shopsphere.userservice.dto.LoginRequest;
import com.shopsphere.userservice.dto.RegisterRequest;
import com.shopsphere.userservice.model.User;
import com.shopsphere.userservice.model.Role;
import com.shopsphere.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // JWT service to generate tokens
    private final JwtService jwtService;

    // Repository to talk to database
    private final UserRepository userRepository;

    // Used to encode passwords
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest request) {

        // Step 1 - Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null,null, "email already exists");
        }

        // Step 2 - Build new user object
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        // Step 3 - Save user to database
        userRepository.save(user);

        // Step 4 - Generate JWT token
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        // Step 5 - Return response with token
        return new AuthResponse(token, user.getRole().name(), "User registered successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // Step 1 - Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2 - Check if password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse(null, null, "Invalid password");
        }

        // Step 3 - Generate JWT token
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        // Step 4 - Return response with token
        return new AuthResponse(token, user.getRole().name(), "Login successful");
    }
}
