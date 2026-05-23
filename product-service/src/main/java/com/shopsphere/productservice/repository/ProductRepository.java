package com.shopsphere.productservice.repository;

import com.shopsphere.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products by seller ID
    // Used when seller wants to see their own products
    List<Product> findBySellerId(Long sellerId);

    // Find all products where stock is greater than zero
    // Used to show only available products to customers
    List<Product> findByStockGreaterThan(Integer stock);

    // Search products by name containing a keyword
    // Used for product search feature
    List<Product> findByNameContainingIgnoreCase(String keyword);

}
