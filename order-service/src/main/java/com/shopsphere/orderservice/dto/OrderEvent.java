package com.shopsphere.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    // Order ID
    private Long orderId;

    // User ID who placed the order
    private Long userId;

    // Product ID that was ordered
    private Long productId;

    // Number of items ordered
    private Integer quantity;

    // Total price of the order
    private Double totalPrice;

    // Current status of the order
    private String status;

}