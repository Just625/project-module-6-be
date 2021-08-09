package com.example.casestudy.controller;

import com.example.casestudy.model.Notification;
import com.example.casestudy.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class SocketController {
    @Autowired
    private INotificationService notificationService;

    @MessageMapping("/notifications")
    @SendTo("/topic/notifications")
    public Notification createNewNotificationUsingSocket(Notification notification){
        return notificationService.save(notification);
    }
}
