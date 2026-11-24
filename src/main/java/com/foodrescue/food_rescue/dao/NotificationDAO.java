package com.foodrescue.food_rescue.dao;

import java.util.List;
import com.foodrescue.food_rescue.model.Notification;

public interface NotificationDAO {

    // Add a notification for a user
    int addNotification(Notification notification);

    // Get notification using ID
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