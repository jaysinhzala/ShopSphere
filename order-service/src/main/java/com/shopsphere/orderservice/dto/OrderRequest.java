package com.shopsphere.orderservice.dto;

import lombok.Data;

@Data
public class OrderRequest {
    // ID of product being ordered
    private Long productId;

    // ID of user placing the order
    private Long userId;

    // Number of items ordered
    private Integer quantity;

    // Delivery address
    private String shippingAddress;
}
