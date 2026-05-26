package com.shopsphere.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserServiceClient {

    // Check if user exists by ID
    @GetMapping("/users/{id}")
    boolean existsById(@PathVariable Long id);

}