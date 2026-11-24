
package com.foodrescue.food_rescue.service;

import java.util.List;
import com.foodrescue.food_rescue.model.Donation;

public interface DonationService {

    // Add a new food donation
    int addDonation(Donation donation);

    // Get donation using ID
    Donation getDonationById(int donationId);

    // Get all available donations
    List<Donation> getAvailableDonations();

    // Get donations added by a particular donor
    List<Donation> getDonationsByDonor(int donorId);

    // Update donation status
    boolean updateDonationStatus(int donationId, String status);

    // Delete a donation
    boolean deleteDonation(int donationId);
}
