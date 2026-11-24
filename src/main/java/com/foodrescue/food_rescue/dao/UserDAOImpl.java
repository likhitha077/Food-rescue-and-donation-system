
package com.foodrescue.food_rescue.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foodrescue.food_rescue.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // REGISTER USER
    @Override
    public int registerUser(User user) {

        String sql = "INSERT INTO users "
                + "(name, email, password, phone, role, address) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getAddress());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // FIND USER BY EMAIL
    @Override
    public User findUserByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> {

            User user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setRole(rs.getString("role"));
            user.setAddress(rs.getString("address"));

            Timestamp timestamp = rs.getTimestamp("created_at");

            if (timestamp != null) {
                user.setCreatedAt(timestamp.toLocalDateTime());
            }

            return user;

        }, email);

        return users.isEmpty() ? null : users.get(0);
    }

    // GET ALL USERS
    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            User user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setRole(rs.getString("role"));
            user.setAddress(rs.getString("address"));

            Timestamp timestamp = rs.getTimestamp("created_at");

            if (timestamp != null) {
                user.setCreatedAt(timestamp.toLocalDateTime());
            }

            return user;
        });
    }
}
