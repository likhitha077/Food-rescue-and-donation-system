

package com.foodrescue.food_rescue.dao;

import java.util.List;
import com.foodrescue.food_rescue.model.Donation;

public interface DonationDAO {

    // Add a new food donation
    int addDonation(Donation donation);

    // Get a donation using its ID
    Donation getDonationById(int donationId);

    // Display all available donations
    List<Donation> getAvailableDonations();

    // Get all donations created by a donor
    List<Donation> getDonationsByDonor(int donorId);

    // Update donation status
    boolean updateDonationStatus(int donationId, String status);

    // Get donations whose collection deadline has passed
    List<Donation> getExpiredDonations();

    // Delete a donation
    boolean deleteDonation(int donationId);
}
