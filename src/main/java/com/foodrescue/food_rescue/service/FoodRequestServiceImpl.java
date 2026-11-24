

package com.foodrescue.food_rescue.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodrescue.food_rescue.dao.FoodRequestDAO;
import com.foodrescue.food_rescue.dao.DonationDAO;
import com.foodrescue.food_rescue.model.Donation;
import com.foodrescue.food_rescue.model.FoodRequest;
import com.foodrescue.food_rescue.model.Notification;

@Service
public class FoodRequestServiceImpl implements FoodRequestService {

    private final FoodRequestDAO foodRequestDAO;
    private final DonationDAO donationDAO;
    private final NotificationService notificationService;

    public FoodRequestServiceImpl(
            FoodRequestDAO foodRequestDAO,
            DonationDAO donationDAO,
            NotificationService notificationService) {

        this.foodRequestDAO = foodRequestDAO;
        this.donationDAO = donationDAO;
        this.notificationService = notificationService;
    }

    // 1. CREATE FOOD REQUEST
    @Override
    @Transactional
    public int createRequest(FoodRequest request) {

        if (request == null) {
            throw new IllegalArgumentException(
                    "Request details cannot be empty");
        }

        if (request.getDonationId() <= 0 ||
                request.getReceiverId() <= 0) {

            throw new IllegalArgumentException(
                    "Invalid donation ID or receiver ID");
        }

        if (request.getRequestedQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Requested quantity must be greater than zero");
        }

        Donation donation =
                donationDAO.getDonationById(request.getDonationId());

        if (donation == null) {
            throw new IllegalArgumentException(
                    "Donation not found");
        }

        // PREVENT DUPLICATE ACTIVE REQUESTS
        if (foodRequestDAO.hasActiveRequest(
                request.getDonationId(),
                request.getReceiverId())) {

            throw new IllegalStateException(
                    "You already have an active request for this donation");
        }

        // CHECK DONATION AVAILABILITY
        if (!"AVAILABLE".equals(donation.getStatus())) {
            throw new IllegalStateException(
                    "This donation is not available");
        }

        // CHECK COLLECTION DEADLINE
        if (donation.getCollectionDeadline() == null ||
                !donation.getCollectionDeadline()
                        .isAfter(LocalDateTime.now())) {

            donationDAO.updateDonationStatus(
                    donation.getDonationId(), "EXPIRED");

            throw new IllegalStateException(
                    "This donation has expired and cannot be requested");
        }

        // CHECK REQUESTED QUANTITY
        if (request.getRequestedQuantity() > donation.getQuantity()) {
            throw new IllegalArgumentException(
                    "Requested quantity exceeds available quantity");
        }

        request.setStatus("PENDING");

        // SAVE REQUEST
        int requestId = foodRequestDAO.createRequest(request);

        // UPDATE DONATION STATUS
        donationDAO.updateDonationStatus(
                donation.getDonationId(), "REQUESTED");

        // NOTIFY DONOR
        Notification notification = new Notification();

        notification.setUserId(donation.getDonorId());

        notification.setMessage(
                "A receiver has requested "
                + request.getRequestedQuantity()
                + " units of your food donation: "
                + donation.getFoodName()
                + ". Please check your dashboard.");

        notification.setRead(false);

        notificationService.addNotification(notification);

        return requestId;
    }

    // 2. GET REQUEST BY ID
    @Override
    public FoodRequest getRequestById(int requestId) {

        if (requestId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid request ID");
        }

        return foodRequestDAO.getRequestById(requestId);
    }

    // 3. GET REQUESTS BY RECEIVER
    @Override
    public List<FoodRequest> getRequestsByReceiver(int receiverId) {

        if (receiverId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid receiver ID");
        }

        return foodRequestDAO.getRequestsByReceiver(receiverId);
    }

    // 4. GET REQUESTS BY DONATION
    @Override
    public List<FoodRequest> getRequestsByDonation(int donationId) {

        if (donationId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid donation ID");
        }

        return foodRequestDAO.getRequestsByDonation(donationId);
    }

    // 5. GET ALL REQUESTS
    @Override
    public List<FoodRequest> getAllRequests() {

        return foodRequestDAO.getAllRequests();
    }

    // 6. UPDATE REQUEST STATUS
    @Override
    @Transactional
    public boolean updateRequestStatus(
            int requestId, String status) {

        if (requestId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid request ID");
        }

        if (status == null ||
                !List.of(
                        "PENDING",
                        "APPROVED",
                        "REJECTED",
                        "COMPLETED",
                        "CANCELLED"
                ).contains(status)) {

            throw new IllegalArgumentException(
                    "Invalid request status");
        }

        FoodRequest request =
                foodRequestDAO.getRequestById(requestId);

        if (request == null) {
            return false;
        }

        boolean updated =
                foodRequestDAO.updateRequestStatus(
                        requestId, status);

        if (updated && "APPROVED".equals(status)) {

            donationDAO.updateDonationStatus(
                    request.getDonationId(), "APPROVED");

        } else if (updated && "REJECTED".equals(status)) {

            Donation donation =
                    donationDAO.getDonationById(
                            request.getDonationId());

            if (donation != null) {

                if (donation.getCollectionDeadline() != null &&
                        donation.getCollectionDeadline()
                                .isAfter(LocalDateTime.now())) {

                    donationDAO.updateDonationStatus(
                            request.getDonationId(), "AVAILABLE");

                } else {

                    donationDAO.updateDonationStatus(
                            request.getDonationId(), "EXPIRED");
                }
            }
        }

        return updated;
    }

    // 7. DELETE REQUEST
    @Override
    public boolean deleteRequest(int requestId) {

        if (requestId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid request ID");
        }

        FoodRequest request =
                foodRequestDAO.getRequestById(requestId);

        if (request == null) {
            return false;
        }

        if (!"PENDING".equals(request.getStatus())) {
            throw new IllegalStateException(
                    "Only pending requests can be deleted");
        }

        return foodRequestDAO.deleteRequest(requestId);
    }
}
