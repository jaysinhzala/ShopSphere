package com.shopsphere.orderservice.feign;

import com.shopsphere.orderservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductServiceClient {

    // Get product details by ID
    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    // Update product stock after order is placed
    @PutMapping("/products/{id}/stock")
    void updateStock(@PathVariable Long id, @RequestParam Integer quantity);

}