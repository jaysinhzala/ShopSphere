package com.shopsphere.orderservice.service;

import com.shopsphere.orderservice.dto.OrderEvent;
import com.shopsphere.orderservice.dto.OrderRequest;
import com.shopsphere.orderservice.dto.OrderResponse;
import com.shopsphere.orderservice.dto.ProductResponse;
import com.shopsphere.orderservice.feign.ProductServiceClient;
import com.shopsphere.orderservice.feign.UserServiceClient;
import com.shopsphere.orderservice.kafka.OrderKafkaProducer;
import com.shopsphere.orderservice.model.Order;
import com.shopsphere.orderservice.model.OrderStatus;
import com.shopsphere.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // Repository to talk to database
    private final OrderRepository orderRepository;

    // Feign client to talk to User Service
    private final UserServiceClient userServiceClient;

    // Feign client to talk to Product Service
    private final ProductServiceClient productServiceClient;

    // Kafka producer to publish order events
    private final OrderKafkaProducer orderKafkaProducer;


    @Override
    public OrderResponse placeOrder(OrderRequest request) {

            // Step 1 - Check if user exists
            boolean userExists = userServiceClient.existsById(request.getUserId());
            if (!userExists) {
                throw new RuntimeException("User not found with id: " + request.getUserId());
            }

            // Step 2 - Get product details
            ProductResponse product = productServiceClient.getProductById(request.getProductId());

            // Step 3 - Check if enough stock available
            if (product.getStock() < request.getQuantity()) {
                throw new RuntimeException("Sorry, We do not have enough stock for the order");
            }

            // Step 4 - Calculate total price
            Double totalPrice = product.getPrice() * request.getQuantity();

            // Step 5 - Create and save order
            Order order = Order.builder()
                    .userId(request.getUserId())
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .totalPrice(totalPrice)
                    .status(OrderStatus.PENDING)
                    .shippingAddress(request.getShippingAddress())
                    .createdAt(LocalDateTime.now())
                    .build();

            Order savedOrder = orderRepository.save(order);

            // Step 6 - Update product stock
            productServiceClient.updateStock(request.getProductId(), request.getQuantity());

            // Step 7 - Publish order event to Kafka
            OrderEvent orderEvent = new OrderEvent(
                    savedOrder.getId(),
                    request.getUserId(),
                    request.getProductId(),
                    request.getQuantity(),
                    totalPrice,
                    OrderStatus.PENDING.name()
            );
            orderKafkaProducer.sendOrderEvent(orderEvent);

            // Step 8 - Return response
            return convertToResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(Long id) {

        // Find order by id - throw exception if not found
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        // Convert and return response
        return convertToResponse(order);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {

        // Find all orders placed by this user
        List<Order> orders = orderRepository.findByUserId(userId);

        // Convert each Order to OrderResponse and return
        return orders.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getOrdersByProductId(Long productId) {

        // Find all orders for this product
        List<Order> orders = orderRepository.findByProductId(productId);

        // Convert each Order to OrderResponse and return
        return orders.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Helper method to convert Order entity to OrderResponse
    private OrderResponse convertToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getUserId(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus().name(),
                order.getShippingAddress(),
                order.getCreatedAt()
        );
    }
}
