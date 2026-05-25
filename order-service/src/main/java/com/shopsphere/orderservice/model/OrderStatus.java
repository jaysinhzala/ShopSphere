package com.shopsphere.orderservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum OrderStatus {
    PENDING,    // order just placed
    CONFIRMED,  // seller confirmed
    SHIPPED,    // on the way
    DELIVERED,  // received
    CANCELLED   // cancelled
}
