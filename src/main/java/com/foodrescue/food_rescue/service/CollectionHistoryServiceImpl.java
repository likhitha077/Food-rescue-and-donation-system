
package com.foodrescue.food_rescue.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foodrescue.food_rescue.dao.CollectionHistoryDAO;
import com.foodrescue.food_rescue.dao.FoodRequestDAO;
import com.foodrescue.food_rescue.model.CollectionHistory;
import com.foodrescue.food_rescue.model.FoodRequest;
import com.foodrescue.food_rescue.model.Notification;

@Service
public class CollectionHistoryServiceImpl
        implements CollectionHistoryService {

    private final CollectionHistoryDAO collectionHistoryDAO;
    private final FoodRequestDAO foodRequestDAO;
    private final NotificationService notificationService;

    public CollectionHistoryServiceImpl(
            CollectionHistoryDAO collectionHistoryDAO,
            FoodRequestDAO foodRequestDAO,
            NotificationService notificationService) {

        this.collectionHistoryDAO = collectionHistoryDAO;
        this.foodRequestDAO = foodRequestDAO;
        this.notificationService = notificationService;
    }

    @Override
    public int addCollection(CollectionHistory collection) {

        if (collection == null) {
            throw new IllegalArgumentException(
                    "Collection details cannot be empty");
        }

        if (collection.getRequestId() <= 0) {
            throw new IllegalArgumentException("Invalid request ID");
        }

        if (collection.getCollectedQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Collected quantity must be greater than zero");
        }

        FoodRequest request = foodRequestDAO.getRequestById(
                collection.getRequestId());

        if (request == null) {
            throw new IllegalArgumentException("Food request not found");
        }

        if (!"APPROVED".equals(request.getStatus())) {
            throw new IllegalStateException(
                    "Only approved requests can be collected");
        }

        if (collectionHistoryDAO.getCollectionByRequestId(
                collection.getRequestId()) != null) {
            throw new IllegalStateException(
                    "Collection record already exists");
        }

        if (collection.getCollectionTime() == null) {
            collection.setCollectionTime(LocalDateTime.now());
        }

        // Save collection record
        int collectionId =
                collectionHistoryDAO.addCollection(collection);

        // Update request status
        foodRequestDAO.updateRequestStatus(
                request.getRequestId(), "COMPLETED");

        // Create notification for receiver
        Notification notification = new Notification();

        notification.setUserId(request.getReceiverId());

        notification.setMessage(
                "Your food collection has been completed successfully. "
                + "Thank you for helping reduce food waste!");

        notification.setRead(false);

        // Save notification
        notificationService.addNotification(notification);

        return collectionId;
    }

    @Override
    public CollectionHistory getCollectionById(int collectionId) {

        if (collectionId <= 0) {
            throw new IllegalArgumentException("Invalid collection ID");
        }

        return collectionHistoryDAO.getCollectionById(collectionId);
    }

    @Override
    public CollectionHistory getCollectionByRequestId(int requestId) {

        if (requestId <= 0) {
            throw new IllegalArgumentException("Invalid request ID");
        }

        return collectionHistoryDAO.getCollectionByRequestId(requestId);
    }

    @Override
    public List<CollectionHistory> getAllCollections() {
        return collectionHistoryDAO.getAllCollections();
    }

    @Override
    public List<CollectionHistory> getCollectionsByDonorId(int donorId) {

        if (donorId <= 0) {
            throw new IllegalArgumentException("Invalid donor ID");
        }

        return collectionHistoryDAO.getCollectionsByDonorId(donorId);
    }

    @Override
    public List<CollectionHistory> getCollectionsByReceiverId(int receiverId) {

        if (receiverId <= 0) {
            throw new IllegalArgumentException("Invalid receiver ID");
        }

        return collectionHistoryDAO.getCollectionsByReceiverId(receiverId);
    }

    @Override
    public boolean deleteCollection(int collectionId) {

        if (collectionId <= 0) {
            throw new IllegalArgumentException("Invalid collection ID");
        }

        return collectionHistoryDAO.deleteCollection(collectionId);
    }
}
