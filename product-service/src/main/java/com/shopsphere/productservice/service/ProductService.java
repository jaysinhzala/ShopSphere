package com.shopsphere.productservice.service;

import com.shopsphere.productservice.dto.ProductRequest;
import com.shopsphere.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    // Create a new product and return saved product details
    ProductResponse  createProduct(ProductRequest request);

    // Get all available products for customers to browse
    List<ProductResponse> getAllProducts();

    // Get a single product by its ID
    ProductResponse getProductById(Long id);

    // Delete a product by its ID
    void deleteProduct(Long id);

    // Update an existing product and return updated details
    ProductResponse updateProduct(Long id, ProductRequest request);

    // Get all products belonging to a specific seller
    List<ProductResponse> getProductsBySeller(Long sellerId);

    // Search products by keyword in product name
    List<ProductResponse> searchProducts(String search);

    // Update product stock after order is placed
    void updateStock(Long id, Integer quantity);
}
