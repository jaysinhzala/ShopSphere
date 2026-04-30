package com.shopsphere.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    // Product unique identifier
    private Long id;

    // Product name
    private String name;

    // Product description
    private String description;

    // Product price
    private Double price;

    // Available stock quantity
    private Integer stock;

    // URL of product image stored in AWS S3
    private String imageUrl;

    // ID of seller who created this product
    private Long sellerId;

}