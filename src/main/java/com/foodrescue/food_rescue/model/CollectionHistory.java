
package com.foodrescue.food_rescue.model;

import java.time.LocalDateTime;

public class CollectionHistory {

    private Integer collectionId;
    private Integer requestId;
    private int collectedQuantity;
    private LocalDateTime collectionTime;
    private String remarks;

    public CollectionHistory() {
    }

    public CollectionHistory(Integer collectionId, Integer requestId,
            int collectedQuantity, LocalDateTime collectionTime,
            String remarks) {

        this.collectionId = collectionId;
        this.requestId = requestId;
        this.collectedQuantity = collectedQuantity;
        this.collectionTime = collectionTime;
        this.remarks = remarks;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getCollectedQuantity() {
        return collectedQuantity;
    }

    public void setCollectedQuantity(int collectedQuantity) {
        this.collectedQuantity = collectedQuantity;
    }

    public LocalDateTime getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(LocalDateTime collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
