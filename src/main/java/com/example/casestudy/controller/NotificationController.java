package com.example.casestudy.controller;

import com.example.casestudy.model.Notification;
import com.example.casestudy.repository.INotificationRepository;
import com.example.casestudy.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("notifications")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @GetMapping
    public ResponseEntity<Iterable<Notification>> getAllNotification() {
        return new ResponseEntity<>(notificationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewNotification(@RequestBody Notification notification){
        notificationService.save(notification);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }
}
