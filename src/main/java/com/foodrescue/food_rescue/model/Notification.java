
package com.foodrescue.food_rescue.model;

import java.time.LocalDateTime;

public class Notification {

    private Integer notificationId;
    private Integer userId;
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public Notification() {
    }

    public Notification(Integer notificationId, Integer userId,
            String message, Boolean isRead,
            LocalDateTime createdAt) {

        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
