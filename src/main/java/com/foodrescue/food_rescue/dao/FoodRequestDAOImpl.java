

package com.foodrescue.food_rescue.dao;

import java.sql.Timestamp;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foodrescue.food_rescue.model.FoodRequest;

@Repository
public class FoodRequestDAOImpl implements FoodRequestDAO {

    private final JdbcTemplate jdbcTemplate;

    public FoodRequestDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Convert database row into FoodRequest object
    private FoodRequest mapRow(
            java.sql.ResultSet rs, int rowNum)
            throws java.sql.SQLException {

        FoodRequest request = new FoodRequest();

        request.setRequestId(rs.getInt("request_id"));
        request.setDonationId(rs.getInt("donation_id"));
        request.setReceiverId(rs.getInt("receiver_id"));
        request.setRequestedQuantity(
                rs.getInt("requested_quantity"));
        request.setStatus(rs.getString("status"));

        Timestamp requestedAt = rs.getTimestamp("requested_at");

        if (requestedAt != null) {
            request.setRequestedAt(
                    requestedAt.toLocalDateTime());
        }

        return request;
    }

    // 1. CREATE FOOD REQUEST
    @Override
    public int createRequest(FoodRequest request) {

        String sql = "INSERT INTO requests "
                + "(donation_id, receiver_id, requested_quantity, status) "
                + "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            var ps = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, request.getDonationId());
            ps.setInt(2, request.getReceiverId());
            ps.setInt(3, request.getRequestedQuantity());
            ps.setString(4,
                    request.getStatus() == null
                            ? "PENDING"
                            : request.getStatus());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // 2. GET REQUEST BY ID
    @Override
    public FoodRequest getRequestById(int requestId) {

        String sql = "SELECT * FROM requests WHERE request_id = ?";

        List<FoodRequest> requests =
                jdbcTemplate.query(sql, this::mapRow, requestId);

        return requests.isEmpty() ? null : requests.get(0);
    }

    // 3. GET REQUESTS BY RECEIVER
    @Override
    public List<FoodRequest> getRequestsByReceiver(int receiverId) {

        String sql = "SELECT * FROM requests WHERE receiver_id = ?";

        return jdbcTemplate.query(sql, this::mapRow, receiverId);
    }

    // 4. GET REQUESTS BY DONATION
    @Override
    public List<FoodRequest> getRequestsByDonation(int donationId) {

        String sql = "SELECT * FROM requests WHERE donation_id = ?";

        return jdbcTemplate.query(sql, this::mapRow, donationId);
    }

    // 5. CHECK ACTIVE REQUEST
    @Override
    public boolean hasActiveRequest(int donationId, int receiverId) {

        String sql = "SELECT COUNT(*) FROM requests "
                + "WHERE donation_id = ? "
                + "AND receiver_id = ? "
                + "AND status IN ('PENDING', 'APPROVED')";

        Integer count = jdbcTemplate.queryForObject(
                sql, Integer.class, donationId, receiverId);

        return count != null && count > 0;
    }

    // 6. GET ALL REQUESTS
    @Override
    public List<FoodRequest> getAllRequests() {

        String sql = "SELECT * FROM requests";

        return jdbcTemplate.query(sql, this::mapRow);
    }

    // 7. UPDATE REQUEST STATUS
    @Override
    public boolean updateRequestStatus(
            int requestId, String status) {

        String sql = "UPDATE requests SET status = ? "
                + "WHERE request_id = ?";

        int rows = jdbcTemplate.update(sql, status, requestId);

        return rows > 0;
    }

    // 8. DELETE REQUEST
    @Override
    public boolean deleteRequest(int requestId) {

        String sql = "DELETE FROM requests WHERE request_id = ?";

        int rows = jdbcTemplate.update(sql, requestId);

        return rows > 0;
    }
}
