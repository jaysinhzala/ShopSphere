package com.shopsphere.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    // Primary key - auto incremented by MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID of user who placed the order
    @Column(nullable = false)
    private Long userId;

    // ID of product that was ordered
    @Column(nullable = false)
    private Long productId;

    // Number of items ordered
    @Column(nullable = false)
    private Integer quantity;

    // Total price of the order
    @Column(nullable = false)
    private Double totalPrice;

    // Current status of the order
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    // Shipping address for the order
    @Column(nullable = false)
    private String shippingAddress;

    // When the order was placed
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}