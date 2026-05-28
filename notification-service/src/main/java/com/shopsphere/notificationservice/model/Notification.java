package com.shopsphere.notificationservice.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    //unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //which order triggered this
    @Column(nullable = false)
    private Long orderId;

    // who to notify
    @Column(nullable = false)
    private Long userId;

    //email address sent to
    @Column(nullable = false)
    private String email;

    //email content
    @Column(nullable = false)
    private String message;

    //when was it sent
    @Column(name = "created_at")
    private LocalDateTime time;
}
