package com.shopsphere.productservice.dto;

import lombok.Data;

@Data
public class ProductRequest {

    // Product name
    private String name;

    // Product description
    private String description;

    // Product price
    private Double price;

    // Available stock quantity
    private Integer stock;

    // ID of seller creating this product
    private Long sellerId;

}