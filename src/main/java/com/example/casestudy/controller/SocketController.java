package com.example.casestudy.controller;

import com.example.casestudy.model.Notification;
import com.example.casestudy.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class SocketController {
    @Autowired
    private INotificationService notificationService;

    @MessageMapping("/notifications")
    @SendTo("/topic/notifications")
    public Notification createNewNotificationUsingSocket(Notification notification){
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        notification.setCreateDate(date);
        notificationService.save(notification);
        return notification;
    }
}
