package com.shopsphere.orderservice.dto;

import lombok.Data;

@Data
public class ProductResponse {

    // Product unique identifier
    private Long id;

    // Product name
    private String name;

    // Product price
    private Double price;

    // Available stock quantity
    private Integer stock;

    // ID of seller who created this product
    private Long sellerId;

}