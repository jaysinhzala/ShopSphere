package com.shopsphere.notificationservice.consumer;

import com.shopsphere.notificationservice.dto.OrderEvent;
import com.shopsphere.notificationservice.model.Notification;
import com.shopsphere.notificationservice.repository.NotificationRepository;
import com.shopsphere.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    // Email service to send emails
    private final EmailService emailService;

    // Repository to save notifications to database
    private final NotificationRepository notificationRepository;

    // Listen to order-placed Kafka topic
    @KafkaListener(topics = "order-placed", groupId = "notification-group")
    public void consumeOrderEvent(OrderEvent orderEvent) {

        System.out.println("Received order event from Kafka: " + orderEvent);

        // Step 1 - Build email message
        String subject = "Order Confirmation - Order #" + orderEvent.getOrderId();
        String body = "Dear Customer,\n\n" +
                "Your order has been placed successfully!\n\n" +
                "Order Details:\n" +
                "Order ID: " + orderEvent.getOrderId() + "\n" +
                "Product ID: " + orderEvent.getProductId() + "\n" +
                "Quantity: " + orderEvent.getQuantity() + "\n" +
                "Total Price: $" + orderEvent.getTotalPrice() + "\n" +
                "Status: " + orderEvent.getStatus() + "\n\n" +
                "Thank you for shopping with ShopSphere!";

        // Step 2 - Send email
        emailService.sendEmail("customer@example.com", subject, body);

        // Step 3 - Save notification to database
        Notification notification = Notification.builder()
                .orderId(orderEvent.getOrderId())
                .userId(orderEvent.getUserId())
                .email("customer@example.com")
                .message(body)
                .time(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        System.out.println("Notification saved to database for order: " + orderEvent.getOrderId());
    }
}
