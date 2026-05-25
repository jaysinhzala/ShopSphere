package com.shopsphere.orderservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    // Unique order ID
    private Long id;

    // ID of product being ordered
    private Long productId;

    // ID of user placing the order
    private Long userId;

    // Number of items ordered
    private Integer quantity;

    // Total price of the order
    private Double totalPrice;

    // Current status of the order
    private String status;

    // Delivery address
    private String shippingAddress;

    // When the order was placed
    private LocalDateTime createdAt;
}
