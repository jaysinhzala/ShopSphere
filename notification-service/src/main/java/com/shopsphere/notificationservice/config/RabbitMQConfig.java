package com.shopsphere.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Queue name from application.properties
    @Value("${rabbitmq.queue.email}")
    private String emailQueue;

    // Exchange name from application.properties
    @Value("${rabbitmq.exchange}")
    private String exchange;

    // Routing key from application.properties
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // Create email notification queue
    @Bean
    public Queue emailQueue() {
        return new Queue(emailQueue, true);
    }

    // Create direct exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    // Bind queue to exchange with routing key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(emailQueue())
                .to(exchange())
                .with(routingKey);
    }

    // Convert messages to JSON
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter(new com.fasterxml.jackson.databind.ObjectMapper());
    }

    // Configure RabbitTemplate with JSON converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}