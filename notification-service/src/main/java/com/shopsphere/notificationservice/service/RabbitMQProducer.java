package com.shopsphere.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    // RabbitTemplate to send messages
    private final RabbitTemplate rabbitTemplate;

    // Exchange name from application.properties
    @Value("${rabbitmq.exchange}")
    private String exchange;

    // Routing key from application.properties
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // Send email message to RabbitMQ queue
    public void sendEmailMessage(String message) {
        System.out.println("Sending message to RabbitMQ: " + message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Message sent to RabbitMQ successfully!");
    }
}
