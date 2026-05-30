package com.shopsphere.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // Route requests to User Service
                .route("user-service", r -> r
                        .path("/users/**")
                        .uri("http://localhost:8081"))

                // Route requests to Product Service
                .route("product-service", r -> r
                        .path("/products/**")
                        .uri("http://localhost:8082"))

                // Route requests to Order Service
                .route("order-service", r -> r
                        .path("/orders/**")
                        .uri("http://localhost:8083"))

                .build();
    }
}