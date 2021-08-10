package com.example.casestudy.controller;

import com.example.casestudy.model.Notification;
import com.example.casestudy.model.User;
import com.example.casestudy.service.notification.INotificationService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class SocketController {
    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IUserService userService;

    @MessageMapping("/notifications")
    @SendTo("/topic/notifications")
    public Notification createNewNotificationUsingSocket(Notification notification){
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        notification.setCreateDate(date);
        Optional<User> user = userService.findById(notification.getSender().getId());
        notification.setSender(user.get());
        notificationService.save(notification);
        return notification;
    }
}
