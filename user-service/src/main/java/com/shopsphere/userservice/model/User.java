package com.shopsphere.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.shopsphere.userservice.model.Role;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    // Primary key - auto incremented by MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User full name - cannot be null
    @Column(nullable = false)
    private String name;

    // User email - cannot be null and must be unique
    @Column(nullable = false, unique = true)
    private String email;

    // User password - will be stored as hashed value
    @Column(nullable = false)
    private String password;

    // User role - stored as string in database
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
