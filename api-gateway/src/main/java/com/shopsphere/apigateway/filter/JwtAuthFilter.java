package com.shopsphere.apigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.List;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    // JWT secret key from application.properties
    @Value("${jwt.secret}")
    private String secretKey;

    // Public endpoints that don't need JWT token
    private final List<String> publicEndpoints = List.of(
            "/users/register",
            "/users/login"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // Get request path
        String path = exchange.getRequest().getURI().getPath();

        // Step 1 - Check if this is a public endpoint
        if (isPublicEndpoint(path)) {
            return chain.filter(exchange);
        }

        // Step 2 - Get Authorization header
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        // Step 3 - Check if Authorization header exists
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Step 4 - Extract token from header
        String token = authHeader.substring(7);

        // Step 5 - Validate token
        try {
            Claims claims = extractClaims(token);

            // Step 6 - Add user info to request headers
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(r -> r.headers(headers -> {
                        headers.add("userId", claims.getSubject());
                        headers.add("userRole", claims.get("role", String.class));
                    }))
                    .build();

            return chain.filter(modifiedExchange);

        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    // Check if endpoint is public
    private boolean isPublicEndpoint(String path) {
        return publicEndpoints.stream()
                .anyMatch(path::equals);
    }

    // Extract claims from JWT token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Get signing key from secret
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Run this filter before all other filters
    @Override
    public int getOrder() {
        return -1;
    }
}