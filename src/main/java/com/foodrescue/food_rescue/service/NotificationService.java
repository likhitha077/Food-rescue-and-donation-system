

package com.foodrescue.food_rescue.service;

import java.util.List;
import com.foodrescue.food_rescue.model.Notification;

public interface NotificationService {

    // Add a notification
    int addNotification(Notification notification);

    // Get notification by ID
    Notification getNotificationById(int notificationId);

    // Get all notifications of a user
    List<Notification> getNotificationsByUser(int userId);

    // Get unread notifications
    List<Notification> getUnreadNotifications(int userId);

    // Mark notification as read
    boolean markAsRead(int notificationId);

    // Delete notification
    boolean deleteNotification(int notificationId);
}
