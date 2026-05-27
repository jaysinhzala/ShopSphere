package com.shopsphere.orderservice.controller;

import com.shopsphere.orderservice.dto.OrderRequest;
import com.shopsphere.orderservice.dto.OrderResponse;
import com.shopsphere.orderservice.dto.ProductResponse;
import com.shopsphere.orderservice.model.Order;
import com.shopsphere.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    // Service layer to handle business logic
    private final OrderService orderService;

    // Place a new order
    // POST http://localhost:8083/orders
    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }

    // Get a single order by its ID
    // GET http://localhost:8083/orders/1
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // Get all orders placed by a specific user
    // GET http://localhost:8083/orders/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    // Get all orders for a specific product
    // GET http://localhost:8083/orders/product/1
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderResponse>> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(orderService.getOrdersByProductId(productId));
    }
}
