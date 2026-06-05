package com.shopsphere.notificationservice.consumer;

import com.shopsphere.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQConsumer {

    // Email service to send emails
    private final EmailService emailService;

    // Listen to email notification queue
    @RabbitListener(queues = "${rabbitmq.queue.email}")
    public void consumeEmailMessage(String message) {

        System.out.println("Received message from RabbitMQ: " + message);

        try {
            // Parse the message and send email
            // Message format: "email|subject|body"
            String[] parts = message.split("\\|");
            String to = parts[0];
            String subject = parts[1];
            String body = parts[2];

            // Send email
            emailService.sendEmail(to, subject, body);

            System.out.println("Email sent successfully to: " + to);

        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}