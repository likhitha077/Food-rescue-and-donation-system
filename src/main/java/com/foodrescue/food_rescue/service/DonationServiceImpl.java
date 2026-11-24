
package com.foodrescue.food_rescue.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foodrescue.food_rescue.dao.DonationDAO;
import com.foodrescue.food_rescue.model.Donation;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationDAO donationDAO;

    public DonationServiceImpl(DonationDAO donationDAO) {
        this.donationDAO = donationDAO;
    }

    // Add a new donation
    @Override
    public int addDonation(Donation donation) {

        if (donation == null) {
            throw new IllegalArgumentException(
                    "Donation details cannot be empty");
        }

        if (donation.getDonorId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid donor ID");
        }

        if (donation.getFoodName() == null ||
                donation.getFoodName().isBlank()) {
            throw new IllegalArgumentException(
                    "Food name is required");
        }

        if (donation.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero");
        }

        if (donation.getPreparationTime() == null ||
                donation.getCollectionDeadline() == null) {
            throw new IllegalArgumentException(
                    "Preparation time and collection deadline are required");
        }

        if (donation.getPickupLocation() == null ||
                donation.getPickupLocation().isBlank()) {
            throw new IllegalArgumentException(
                    "Pickup location is required");
        }

        if (!donation.getCollectionDeadline()
                .isAfter(donation.getPreparationTime())) {
            throw new IllegalArgumentException(
                    "Collection deadline must be after preparation time");
        }

        if (donation.getCollectionDeadline()
                .isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Collection deadline cannot be in the past");
        }

        donation.setStatus("AVAILABLE");

        return donationDAO.addDonation(donation);
    }

    // Get donation by ID
    @Override
    public Donation getDonationById(int donationId) {

        if (donationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid donation ID");
        }

        return donationDAO.getDonationById(donationId);
    }

    // Get all available donations
    @Override
    public List<Donation> getAvailableDonations() {

        return donationDAO.getAvailableDonations();
    }

    // Get donations by donor
    @Override
    public List<Donation> getDonationsByDonor(int donorId) {

        if (donorId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid donor ID");
        }

        return donationDAO.getDonationsByDonor(donorId);
    }

    // Update donation status
    @Override
    public boolean updateDonationStatus(
            int donationId, String status) {

        if (donationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid donation ID");
        }

        if (status == null ||
                !List.of("AVAILABLE", "REQUESTED", "APPROVED",
                         "COLLECTED", "EXPIRED", "CANCELLED")
                        .contains(status)) {
            throw new IllegalArgumentException(
                    "Invalid donation status");
        }

        return donationDAO.updateDonationStatus(
                donationId, status);
    }

    // Delete donation
    @Override
    public boolean deleteDonation(int donationId) {

        if (donationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid donation ID");
        }

        Donation donation =
                donationDAO.getDonationById(donationId);

        if (donation == null) {
            return false;
        }

        if (!"AVAILABLE".equals(donation.getStatus())) {
            throw new IllegalStateException(
                    "Only available donations can be deleted");
        }

        return donationDAO.deleteDonation(donationId);
    }
}
