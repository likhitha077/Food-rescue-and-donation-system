
package com.foodrescue.food_rescue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.foodrescue.food_rescue.model.Donation;
import com.foodrescue.food_rescue.service.DonationService;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // Add a new food donation
    @PostMapping
    public int addDonation(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }

    // Get donation using ID
    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable int id) {
        return donationService.getDonationById(id);
    }

    // Get all available donations
    @GetMapping("/available")
    public List<Donation> getAvailableDonations() {
        return donationService.getAvailableDonations();
    }

    // Get donations added by a particular donor
    @GetMapping("/donor/{donorId}")
    public List<Donation> getDonationsByDonor(
            @PathVariable int donorId) {
        return donationService.getDonationsByDonor(donorId);
    }

    // Update donation status
    @PutMapping("/{id}/status")
    public boolean updateDonationStatus(
            @PathVariable int id,
            @RequestParam String status) {
        return donationService.updateDonationStatus(id, status);
    }

    // Delete a donation
    @DeleteMapping("/{id}")
    public boolean deleteDonation(@PathVariable int id) {
        return donationService.deleteDonation(id);
    }
}

