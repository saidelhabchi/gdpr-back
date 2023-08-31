package com.example.Application.service;

import com.example.Application.model.Notification;
import com.example.Application.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    public List<Notification> readAll() {
        return notificationRepository.findAll();
    }

    public String markAllAsRead() {
        List<Notification> all = notificationRepository.findAll();
        for (Notification notification: all) {
            notification.setRead(true);
        }
        notificationRepository.saveAll(all);
        return "success";
    }
    public String markOneAsRead(int id){
        Optional<Notification> toBeRead = notificationRepository.findById(id);
        if(toBeRead.isPresent()){
            Notification notification = toBeRead.get();
            notification.setRead(true);
            notificationRepository.save(notification);
            return "success";
        }else{
            return "this notification isn't present";
        }
    }

    public Long UnreadNotifications() {
        return notificationRepository.countByIsRead(false);
    }
}
