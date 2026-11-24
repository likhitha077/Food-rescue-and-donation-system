

package com.foodrescue.food_rescue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodrescue.food_rescue.dao.NotificationDAO;
import com.foodrescue.food_rescue.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDAO notificationDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    // Add notification
    @Override
    public int addNotification(Notification notification) {

        if (notification == null) {
            throw new IllegalArgumentException(
                    "Notification cannot be empty");
        }

        if (notification.getUserId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid user ID");
        }

        if (notification.getMessage() == null ||
                notification.getMessage().isBlank()) {
            throw new IllegalArgumentException(
                    "Notification message is required");
        }

        notification.setRead(false);

        return notificationDAO.addNotification(notification);
    }

    // Get notification by ID
    @Override
    public Notification getNotificationById(int notificationId) {

        if (notificationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid notification ID");
        }

        return notificationDAO.getNotificationById(notificationId);
    }

    // Get all notifications of a user
    @Override
    public List<Notification> getNotificationsByUser(int userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid user ID");
        }

        return notificationDAO.getNotificationsByUser(userId);
    }

    // Get unread notifications
    @Override
    public List<Notification> getUnreadNotifications(int userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid user ID");
        }

        return notificationDAO.getUnreadNotifications(userId);
    }

    // Mark notification as read
    @Override
    public boolean markAsRead(int notificationId) {

        if (notificationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid notification ID");
        }

        return notificationDAO.markAsRead(notificationId);
    }

    // Delete notification
    @Override
    public boolean deleteNotification(int notificationId) {

        if (notificationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid notification ID");
        }

        return notificationDAO.deleteNotification(notificationId);
    }
}
