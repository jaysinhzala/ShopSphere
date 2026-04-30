package com.shopsphere.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    // Primary key - auto incremented by MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Product name - cannot be null
    @Column(nullable = false)
    private String name;

    // Product description
    @Column(length = 1000)
    private String description;

    // Product price - cannot be null
    @Column(nullable = false)
    private Double price;

    // Available stock quantity
    @Column(nullable = false)
    private Integer stock;

    // URL of product image stored in AWS S3
    @Column(name = "image_url")
    private String imageUrl;

    // ID of seller who created this product
    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

}