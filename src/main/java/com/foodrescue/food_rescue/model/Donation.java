
package com.foodrescue.food_rescue.model;

import java.time.LocalDateTime;

public class Donation {

    private Integer donationId;
    private int donorId;
    private String foodName;
    private String category;
    private int quantity;
    private LocalDateTime preparationTime;
    private String pickupLocation;
    private LocalDateTime collectionDeadline;
    private String description;
    private String imagePath;
    private String status;
    private LocalDateTime createdAt;

    public Donation() {
    }

    public Donation(Integer donationId, int donorId, String foodName,
            String category, int quantity,
            LocalDateTime preparationTime, String pickupLocation,
            LocalDateTime collectionDeadline, String description,
            String imagePath, String status, LocalDateTime createdAt) {

        this.donationId = donationId;
        this.donorId = donorId;
        this.foodName = foodName;
        this.category = category;
        this.quantity = quantity;
        this.preparationTime = preparationTime;
        this.pickupLocation = pickupLocation;
        this.collectionDeadline = collectionDeadline;
        this.description = description;
        this.imagePath = imagePath;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(LocalDateTime preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public LocalDateTime getCollectionDeadline() {
        return collectionDeadline;
    }

    public void setCollectionDeadline(LocalDateTime collectionDeadline) {
        this.collectionDeadline = collectionDeadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
