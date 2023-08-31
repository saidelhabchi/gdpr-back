package com.example.Application.controller;

import com.example.Application.model.Notification;
import com.example.Application.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification/")
@CrossOrigin("http://localhost:3000/")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @GetMapping("/read-all")
    public List<Notification> readAll(){
        return notificationService.readAll();
    }

    @PostMapping("/markAllAsRead")
    public ResponseEntity<String> markAllAsRead(){
        notificationService.markAllAsRead();
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/markAsRead/{id}")
    public ResponseEntity<String> markAsRead(@PathVariable int id){
        notificationService.markOneAsRead(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/allUnread")
    public ResponseEntity<Long> isAllUnread(){
        return new ResponseEntity<>(notificationService.UnreadNotifications(),HttpStatus.OK);
    }
}
