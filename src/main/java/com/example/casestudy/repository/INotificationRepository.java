package com.example.casestudy.repository;

import com.example.casestudy.model.Notification;
import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select n from Notification n where n.recieverId = ?1 and n.status = false")
    Iterable<Notification> findAllByStatusIsFalseAndUser(Long id);

    @Query("select n from Notification n where n.recieverId = ?1 order by n.createDate desc")
    Iterable<Notification> findAllDateDesc(Long id);
}
