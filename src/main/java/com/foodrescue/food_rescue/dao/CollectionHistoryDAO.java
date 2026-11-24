
package com.foodrescue.food_rescue.dao;

import java.util.List;
import com.foodrescue.food_rescue.model.CollectionHistory;

public interface CollectionHistoryDAO {

    int addCollection(CollectionHistory collection);

    CollectionHistory getCollectionById(int collectionId);

    CollectionHistory getCollectionByRequestId(int requestId);

    List<CollectionHistory> getAllCollections();

    // Get collections belonging to a particular donor
    List<CollectionHistory> getCollectionsByDonorId(int donorId);

    // Get collections belonging to a particular receiver
    List<CollectionHistory> getCollectionsByReceiverId(int receiverId);

    boolean deleteCollection(int collectionId);
}
