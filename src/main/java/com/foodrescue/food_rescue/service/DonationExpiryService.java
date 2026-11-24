

package com.foodrescue.food_rescue.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.foodrescue.food_rescue.dao.DonationDAO;
import com.foodrescue.food_rescue.model.Donation;
import com.foodrescue.food_rescue.model.Notification;

@Service
public class DonationExpiryService {

    private final DonationDAO donationDAO;
    private final NotificationService notificationService;

    public DonationExpiryService(
            DonationDAO donationDAO,
            NotificationService notificationService) {

        this.donationDAO = donationDAO;
        this.notificationService = notificationService;
    }

    // Check for expired donations every 1 minute
    @Scheduled(fixedRate = 60000)
    public void checkExpiredDonations() {

        List<Donation> expiredDonations =
                donationDAO.getExpiredDonations();

        for (Donation donation : expiredDonations) {

            boolean updated = donationDAO.updateDonationStatus(
                    donation.getDonationId(), "EXPIRED");

            if (updated) {

                Notification notification = new Notification();

                notification.setUserId(donation.getDonorId());

                notification.setMessage(
                        "Your food donation '" + donation.getFoodName()
                        + "' has expired because its collection deadline has passed.");

                notification.setRead(false);

                notificationService.addNotification(notification);

                System.out.println(
                        "Donation ID " + donation.getDonationId()
                        + " expired. Donor notification sent.");
            }
        }
    }
}
