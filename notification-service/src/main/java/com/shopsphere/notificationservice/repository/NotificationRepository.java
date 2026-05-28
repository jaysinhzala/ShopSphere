package com.shopsphere.notificationservice.repository;

import com.shopsphere.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Find all notifications sent to a specific user
    List<Notification> findByUserId(Long userId);

    // Find all notifications for a specific order
    List<Notification> findByOrderId(Long orderId);
}
