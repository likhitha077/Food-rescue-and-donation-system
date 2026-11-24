
package com.foodrescue.food_rescue.service;

import java.util.List;
import com.foodrescue.food_rescue.model.FoodRequest;

public interface FoodRequestService {

    // Receiver requests food
    int createRequest(FoodRequest request);

    // Get request using ID
    FoodRequest getRequestById(int requestId);

    // Get all requests made by a receiver
    List<FoodRequest> getRequestsByReceiver(int receiverId);

    // Get all requests for a donation
    List<FoodRequest> getRequestsByDonation(int donationId);

    // Get all requests
    List<FoodRequest> getAllRequests();

    // Approve or reject a request
    boolean updateRequestStatus(int requestId, String status);

    // Cancel a request
    boolean deleteRequest(int requestId);
}
