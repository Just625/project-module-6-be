package com.example.casestudy.service.notification;

import com.example.casestudy.model.Notification;
import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.INotificationRepository;
import com.example.casestudy.repository.IPlaylistRepository;
import com.example.casestudy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public Iterable<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Iterable<Notification> findAllByStatusIsFalseAndUser(Long id) {
        return notificationRepository.findAllByStatusIsFalseAndUser(id);
    }

    @Override
    public Iterable<Notification> findAllDateDesc(Long id) {
        return notificationRepository.findAllDateDesc(id);
    }
}
