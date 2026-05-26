package com.shopsphere.orderservice.repository;

import com.shopsphere.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find all orders placed by a specific user
    // Used when customer wants to see their order history
    List<Order> findByProductId(Long productId);

    // Find all orders for a specific product
    // Used when seller wants to see orders for their product
    List<Order> findByUserId(Long userId);
}
