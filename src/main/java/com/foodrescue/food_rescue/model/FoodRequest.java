


package com.foodrescue.food_rescue.model;

import java.time.LocalDateTime;

public class FoodRequest {

    private Integer requestId;
    private Integer donationId;
    private Integer receiverId;
    private int requestedQuantity;
    private String status;
    private LocalDateTime requestedAt;

    public FoodRequest() {
    }

    public FoodRequest(Integer requestId, Integer donationId,
            Integer receiverId, int requestedQuantity,
            String status, LocalDateTime requestedAt) {

        this.requestId = requestId;
        this.donationId = donationId;
        this.receiverId = receiverId;
        this.requestedQuantity = requestedQuantity;
        this.status = status;
        this.requestedAt = requestedAt;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}
