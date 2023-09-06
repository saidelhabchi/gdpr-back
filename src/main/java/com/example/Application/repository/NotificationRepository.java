package com.example.Application.repository;

import com.example.Application.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    long countByIsRead(boolean isRead);
    Notification findByDemandeId(int id);
}
