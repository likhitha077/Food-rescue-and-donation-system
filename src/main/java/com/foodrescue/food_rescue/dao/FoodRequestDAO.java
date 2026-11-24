

package com.foodrescue.food_rescue.dao;

import java.util.List;
import com.foodrescue.food_rescue.model.FoodRequest;

public interface FoodRequestDAO {

    // Receiver requests food
    int createRequest(FoodRequest request);

    // Get request using request ID
    FoodRequest getRequestById(int requestId);

    // Get all requests made by a receiver
    List<FoodRequest> getRequestsByReceiver(int receiverId);

    // Get all requests for a particular donation
    List<FoodRequest> getRequestsByDonation(int donationId);

    // Check whether receiver already has an active request
    boolean hasActiveRequest(int donationId, int receiverId);

    // Get all food requests
    List<FoodRequest> getAllRequests();

    // Update request status
    boolean updateRequestStatus(int requestId, String status);

    // Cancel a request
    boolean deleteRequest(int requestId);
}
