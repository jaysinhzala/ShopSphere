package com.shopsphere.orderservice.kafka;

import com.shopsphere.orderservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {

    // Kafka topic name where we publish order events
    private static final String TOPIC = "order-placed";

    // KafkaTemplate is used to send messages to Kafka
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    // Publish order event to Kafka topic
    public void sendOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(TOPIC, orderEvent);
        System.out.println("Order event sent to Kafka topic: " + TOPIC);
    }
}