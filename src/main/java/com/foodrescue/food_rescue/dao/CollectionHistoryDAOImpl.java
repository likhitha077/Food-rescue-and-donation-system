
package com.foodrescue.food_rescue.dao;

import java.sql.Timestamp;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foodrescue.food_rescue.model.CollectionHistory;

@Repository
public class CollectionHistoryDAOImpl implements CollectionHistoryDAO {

    private final JdbcTemplate jdbcTemplate;

    public CollectionHistoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private CollectionHistory mapRow(
            java.sql.ResultSet rs, int rowNum)
            throws java.sql.SQLException {

        CollectionHistory collection = new CollectionHistory();

        collection.setCollectionId(rs.getInt("collection_id"));
        collection.setRequestId(rs.getInt("request_id"));
        collection.setCollectedQuantity(rs.getInt("collected_quantity"));

        Timestamp collectionTime = rs.getTimestamp("collection_time");

        if (collectionTime != null) {
            collection.setCollectionTime(
                    collectionTime.toLocalDateTime());
        }

        collection.setRemarks(rs.getString("remarks"));

        return collection;
    }

    @Override
    public int addCollection(CollectionHistory collection) {

        String sql = "INSERT INTO collection_history "
                   + "(request_id, collected_quantity, collection_time, remarks) "
                   + "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            var ps = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, collection.getRequestId());
            ps.setInt(2, collection.getCollectedQuantity());
            ps.setTimestamp(3,
                    Timestamp.valueOf(collection.getCollectionTime()));
            ps.setString(4, collection.getRemarks());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public CollectionHistory getCollectionById(int collectionId) {

        String sql = "SELECT * FROM collection_history "
                   + "WHERE collection_id = ?";

        List<CollectionHistory> collections =
                jdbcTemplate.query(sql, this::mapRow, collectionId);

        return collections.isEmpty() ? null : collections.get(0);
    }

    @Override
    public CollectionHistory getCollectionByRequestId(int requestId) {

        String sql = "SELECT * FROM collection_history "
                   + "WHERE request_id = ?";

        List<CollectionHistory> collections =
                jdbcTemplate.query(sql, this::mapRow, requestId);

        return collections.isEmpty() ? null : collections.get(0);
    }

    @Override
    public List<CollectionHistory> getAllCollections() {

        String sql = "SELECT * FROM collection_history "
                   + "ORDER BY collection_time DESC";

        return jdbcTemplate.query(sql, this::mapRow);
    }

    @Override
    public List<CollectionHistory> getCollectionsByDonorId(int donorId) {

        String sql = "SELECT ch.* FROM collection_history ch "
                   + "JOIN requests r ON ch.request_id = r.request_id "
                   + "JOIN donations d ON r.donation_id = d.donation_id "
                   + "WHERE d.donor_id = ? "
                   + "ORDER BY ch.collection_time DESC";

        return jdbcTemplate.query(sql, this::mapRow, donorId);
    }

    // Get collections belonging to a particular receiver
    @Override
    public List<CollectionHistory> getCollectionsByReceiverId(int receiverId) {

        String sql = "SELECT ch.* FROM collection_history ch "
                   + "JOIN requests r ON ch.request_id = r.request_id "
                   + "WHERE r.receiver_id = ? "
                   + "ORDER BY ch.collection_time DESC";

        return jdbcTemplate.query(sql, this::mapRow, receiverId);
    }

    @Override
    public boolean deleteCollection(int collectionId) {

        String sql = "DELETE FROM collection_history "
                   + "WHERE collection_id = ?";

        int rows = jdbcTemplate.update(sql, collectionId);

        return rows > 0;
    }
}
