package com.example.casestudy.controller;

import com.example.casestudy.model.Notification;
import com.example.casestudy.model.User;
import com.example.casestudy.service.notification.INotificationService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private INotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User currentUser = userOptional.get();
        currentUser.setName(user.getName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setAvatarUrl(user.getAvatarUrl());
        return new ResponseEntity<>(userService.save(currentUser), HttpStatus.OK);
    }

    @PutMapping("/changePass/{id}/{oldPass}")
    public ResponseEntity<?> changePass(@PathVariable Long id, @RequestBody User user, @PathVariable String oldPass) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user1 = userOptional.get();
        if (!passwordEncoder.matches(oldPass, user1.getPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("old pass not match");
        }
        if (passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("duplicate password");
        }
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.save(user1), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/notifications")
    public ResponseEntity<Iterable<Notification>> getAllNotificationByUser(@PathVariable Long id) {
        return new ResponseEntity<>(notificationService.findAllByStatusIsFalseAndUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/notifications-desc")
    public ResponseEntity<Iterable<Notification>> getAllNotificationByUserDateDesc(@PathVariable Long id) {
        return new ResponseEntity<>(notificationService.findAllDateDesc(id), HttpStatus.OK);
    }
}
