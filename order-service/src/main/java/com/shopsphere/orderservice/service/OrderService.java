package com.shopsphere.orderservice.service;

import com.shopsphere.orderservice.dto.OrderRequest;
import com.shopsphere.orderservice.dto.OrderResponse;
import java.util.List;

public interface OrderService {

    // Place a new order and return order details
    OrderResponse placeOrder(OrderRequest request);

    // Get a single order by its ID
    OrderResponse getOrderById(Long id);

    // Get all orders placed by a specific user
    List<OrderResponse> getOrdersByUserId(Long userId);

    // Get all orders for a specific product
    List<OrderResponse> getOrdersByProductId(Long productId);

}