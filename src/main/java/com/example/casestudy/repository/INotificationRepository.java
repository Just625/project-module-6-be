package com.example.casestudy.repository;

import com.example.casestudy.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepository extends JpaRepository<Notification, Long> {

}
