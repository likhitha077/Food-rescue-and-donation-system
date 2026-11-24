
package com.foodrescue.food_rescue.dao;

import java.sql.Timestamp;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foodrescue.food_rescue.model.Notification;

@Repository
public class NotificationDAOImpl implements NotificationDAO {

    private final JdbcTemplate jdbcTemplate;

    public NotificationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Convert database row into Notification object
    private Notification mapRow(java.sql.ResultSet rs, int rowNum)
            throws java.sql.SQLException {

        Notification notification = new Notification();

        notification.setNotificationId(rs.getInt("notification_id"));
        notification.setUserId(rs.getInt("user_id"));
        notification.setMessage(rs.getString("message"));
        notification.setRead(rs.getBoolean("is_read"));

        Timestamp createdAt = rs.getTimestamp("created_at");

        if (createdAt != null) {
            notification.setCreatedAt(createdAt.toLocalDateTime());
        }

        return notification;
    }

    // Add notification
    @Override
    public int addNotification(Notification notification) {

        String sql = "INSERT INTO notifications (user_id, message, is_read) "
                   + "VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, notification.getUserId());
            ps.setString(2, notification.getMessage());
            ps.setBoolean(3, notification.isRead());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // Get notification by ID
    @Override
    public Notification getNotificationById(int notificationId) {

        String sql = "SELECT * FROM notifications "
                   + "WHERE notification_id = ?";

        List<Notification> notifications =
                jdbcTemplate.query(sql, this::mapRow, notificationId);

        return notifications.isEmpty() ? null : notifications.get(0);
    }

    // Get all notifications of a user
    @Override
    public List<Notification> getNotificationsByUser(int userId) {

        String sql = "SELECT * FROM notifications "
                   + "WHERE user_id = ? ORDER BY created_at DESC";

        return jdbcTemplate.query(sql, this::mapRow, userId);
    }

    // Get unread notifications
    @Override
    public List<Notification> getUnreadNotifications(int userId) {

        String sql = "SELECT * FROM notifications "
                   + "WHERE user_id = ? AND is_read = FALSE "
                   + "ORDER BY created_at DESC";

        return jdbcTemplate.query(sql, this::mapRow, userId);
    }

    // Mark notification as read
    @Override
    public boolean markAsRead(int notificationId) {

        String sql = "UPDATE notifications "
                   + "SET is_read = TRUE WHERE notification_id = ?";

        int rows = jdbcTemplate.update(sql, notificationId);

        return rows > 0;
    }

    // Delete notification
    @Override
    public boolean deleteNotification(int notificationId) {

        String sql = "DELETE FROM notifications "
                   + "WHERE notification_id = ?";

        int rows = jdbcTemplate.update(sql, notificationId);

        return rows > 0;
    }
}

