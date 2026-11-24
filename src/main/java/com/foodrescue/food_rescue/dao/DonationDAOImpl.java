
package com.foodrescue.food_rescue.dao;

import java.sql.Timestamp;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foodrescue.food_rescue.model.Donation;

@Repository
public class DonationDAOImpl implements DonationDAO {

    private final JdbcTemplate jdbcTemplate;

    public DonationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 1. ADD A NEW DONATION
    @Override
    public int addDonation(Donation donation) {

        String sql = "INSERT INTO donations "
                + "(donor_id, food_name, category, quantity, "
                + "preparation_time, pickup_location, "
                + "collection_deadline, description, image_path, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            var ps = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, donation.getDonorId());
            ps.setString(2, donation.getFoodName());
            ps.setString(3, donation.getCategory());
            ps.setInt(4, donation.getQuantity());
            ps.setTimestamp(5,
                    Timestamp.valueOf(donation.getPreparationTime()));
            ps.setString(6, donation.getPickupLocation());
            ps.setTimestamp(7,
                    Timestamp.valueOf(donation.getCollectionDeadline()));
            ps.setString(8, donation.getDescription());
            ps.setString(9, donation.getImagePath());
            ps.setString(10, donation.getStatus());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // 2. GET DONATION BY ID
    @Override
    public Donation getDonationById(int donationId) {

        String sql = "SELECT * FROM donations WHERE donation_id = ?";

        List<Donation> donations = jdbcTemplate.query(
                sql, (rs, rowNum) -> {

            Donation donation = new Donation();

            donation.setDonationId(rs.getInt("donation_id"));
            donation.setDonorId(rs.getInt("donor_id"));
            donation.setFoodName(rs.getString("food_name"));
            donation.setCategory(rs.getString("category"));
            donation.setQuantity(rs.getInt("quantity"));

            Timestamp preparationTime =
                    rs.getTimestamp("preparation_time");

            if (preparationTime != null) {
                donation.setPreparationTime(
                        preparationTime.toLocalDateTime());
            }

            donation.setPickupLocation(
                    rs.getString("pickup_location"));

            Timestamp deadline =
                    rs.getTimestamp("collection_deadline");

            if (deadline != null) {
                donation.setCollectionDeadline(
                        deadline.toLocalDateTime());
            }

            donation.setDescription(rs.getString("description"));
            donation.setImagePath(rs.getString("image_path"));
            donation.setStatus(rs.getString("status"));

            Timestamp createdAt = rs.getTimestamp("created_at");

            if (createdAt != null) {
                donation.setCreatedAt(createdAt.toLocalDateTime());
            }

            return donation;

        }, donationId);

        return donations.isEmpty() ? null : donations.get(0);
    }

    // 3. GET ALL AVAILABLE DONATIONS
    @Override
    public List<Donation> getAvailableDonations() {

        String sql = "SELECT * FROM donations WHERE status = 'AVAILABLE'";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Donation donation = new Donation();

            donation.setDonationId(rs.getInt("donation_id"));
            donation.setDonorId(rs.getInt("donor_id"));
            donation.setFoodName(rs.getString("food_name"));
            donation.setCategory(rs.getString("category"));
            donation.setQuantity(rs.getInt("quantity"));

            Timestamp preparationTime =
                    rs.getTimestamp("preparation_time");

            if (preparationTime != null) {
                donation.setPreparationTime(
                        preparationTime.toLocalDateTime());
            }

            donation.setPickupLocation(
                    rs.getString("pickup_location"));

            Timestamp deadline =
                    rs.getTimestamp("collection_deadline");

            if (deadline != null) {
                donation.setCollectionDeadline(
                        deadline.toLocalDateTime());
            }

            donation.setDescription(rs.getString("description"));
            donation.setImagePath(rs.getString("image_path"));
            donation.setStatus(rs.getString("status"));

            Timestamp createdAt = rs.getTimestamp("created_at");

            if (createdAt != null) {
                donation.setCreatedAt(createdAt.toLocalDateTime());
            }

            return donation;
        });
    }

    // 4. GET DONATIONS BY DONOR
    @Override
    public List<Donation> getDonationsByDonor(int donorId) {

        String sql = "SELECT * FROM donations WHERE donor_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Donation donation = new Donation();

            donation.setDonationId(rs.getInt("donation_id"));
            donation.setDonorId(rs.getInt("donor_id"));
            donation.setFoodName(rs.getString("food_name"));
            donation.setCategory(rs.getString("category"));
            donation.setQuantity(rs.getInt("quantity"));
            donation.setPreparationTime(
                    rs.getTimestamp("preparation_time").toLocalDateTime());
            donation.setPickupLocation(
                    rs.getString("pickup_location"));
            donation.setCollectionDeadline(
                    rs.getTimestamp("collection_deadline").toLocalDateTime());
            donation.setDescription(rs.getString("description"));
            donation.setImagePath(rs.getString("image_path"));
            donation.setStatus(rs.getString("status"));

            Timestamp createdAt = rs.getTimestamp("created_at");

            if (createdAt != null) {
                donation.setCreatedAt(createdAt.toLocalDateTime());
            }

            return donation;
        }, donorId);
    }

    // 5. UPDATE DONATION STATUS
    @Override
    public boolean updateDonationStatus(int donationId, String status) {

        String sql = "UPDATE donations SET status = ? "
                + "WHERE donation_id = ?";

        int rows = jdbcTemplate.update(sql, status, donationId);

        return rows > 0;
    }

    // 6. GET EXPIRED DONATIONS
    @Override
    public List<Donation> getExpiredDonations() {

        String sql = "SELECT * FROM donations "
                + "WHERE collection_deadline < NOW() "
                + "AND status = 'AVAILABLE'";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Donation donation = new Donation();

            donation.setDonationId(rs.getInt("donation_id"));
            donation.setDonorId(rs.getInt("donor_id"));
            donation.setFoodName(rs.getString("food_name"));
            donation.setCategory(rs.getString("category"));
            donation.setQuantity(rs.getInt("quantity"));

            Timestamp preparationTime =
                    rs.getTimestamp("preparation_time");

            if (preparationTime != null) {
                donation.setPreparationTime(
                        preparationTime.toLocalDateTime());
            }

            donation.setPickupLocation(
                    rs.getString("pickup_location"));

            Timestamp deadline =
                    rs.getTimestamp("collection_deadline");

            if (deadline != null) {
                donation.setCollectionDeadline(
                        deadline.toLocalDateTime());
            }

            donation.setDescription(rs.getString("description"));
            donation.setImagePath(rs.getString("image_path"));
            donation.setStatus(rs.getString("status"));

            Timestamp createdAt = rs.getTimestamp("created_at");

            if (createdAt != null) {
                donation.setCreatedAt(createdAt.toLocalDateTime());
            }

            return donation;
        });
    }

    // 7. DELETE DONATION
    @Override
    public boolean deleteDonation(int donationId) {

        String sql = "DELETE FROM donations WHERE donation_id = ?";

        int rows = jdbcTemplate.update(sql, donationId);

        return rows > 0;
    }
}
