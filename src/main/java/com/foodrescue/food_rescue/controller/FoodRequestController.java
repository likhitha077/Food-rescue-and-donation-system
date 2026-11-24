
package com.foodrescue.food_rescue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.foodrescue.food_rescue.model.FoodRequest;
import com.foodrescue.food_rescue.service.FoodRequestService;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class FoodRequestController {

    private final FoodRequestService foodRequestService;

    public FoodRequestController(FoodRequestService foodRequestService) {
        this.foodRequestService = foodRequestService;
    }

    // Create a food request
    @PostMapping
    public int createRequest(@RequestBody FoodRequest request) {
        return foodRequestService.createRequest(request);
    }

    // Get request using ID
    @GetMapping("/{id}")
    public FoodRequest getRequestById(@PathVariable int id) {
        return foodRequestService.getRequestById(id);
    }

    // Get all requests
    @GetMapping
    public List<FoodRequest> getAllRequests() {
        return foodRequestService.getAllRequests();
    }

    // Get requests made by a receiver
    @GetMapping("/receiver/{receiverId}")
    public List<FoodRequest> getRequestsByReceiver(
            @PathVariable int receiverId) {
        return foodRequestService.getRequestsByReceiver(receiverId);
    }

    // Get requests for a particular donation
    @GetMapping("/donation/{donationId}")
    public List<FoodRequest> getRequestsByDonation(
            @PathVariable int donationId) {
        return foodRequestService.getRequestsByDonation(donationId);
    }

    // Update request status
    @PutMapping("/{id}/status")
    public boolean updateRequestStatus(
            @PathVariable int id,
            @RequestParam String status) {
        return foodRequestService.updateRequestStatus(id, status);
    }

    // Delete a request
    @DeleteMapping("/{id}")
    public boolean deleteRequest(@PathVariable int id) {
        return foodRequestService.deleteRequest(id);
    }
}
