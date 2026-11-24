
package com.foodrescue.food_rescue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.foodrescue.food_rescue.model.Notification;
import com.foodrescue.food_rescue.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Add a notification
    @PostMapping
    public int addNotification(@RequestBody Notification notification) {
        return notificationService.addNotification(notification);
    }

    // Get notification using ID
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable int id) {
        return notificationService.getNotificationById(id);
    }

    // Get all notifications of a user
    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUser(
            @PathVariable int userId) {
        return notificationService.getNotificationsByUser(userId);
    }

    // Get unread notifications
    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotifications(
            @PathVariable int userId) {
        return notificationService.getUnreadNotifications(userId);
    }

    // Mark notification as read
    @PutMapping("/{id}/read")
    public boolean markAsRead(@PathVariable int id) {
        return notificationService.markAsRead(id);
    }

    // Delete notification
    @DeleteMapping("/{id}")
    public boolean deleteNotification(@PathVariable int id) {
        return notificationService.deleteNotification(id);
    }
}

