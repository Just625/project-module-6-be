package com.example.casestudy.service.notification;

import com.example.casestudy.model.Notification;

import com.example.casestudy.model.User;
import com.example.casestudy.service.IGeneralService;


public interface INotificationService extends IGeneralService<Notification> {
    Iterable<Notification> findAllByStatusIsFalseAndUser(Long id);

    Iterable<Notification> findAllDateDesc(Long id);
}
