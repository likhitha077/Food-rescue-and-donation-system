
package com.foodrescue.food_rescue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.foodrescue.food_rescue.model.CollectionHistory;
import com.foodrescue.food_rescue.service.CollectionHistoryService;

@RestController
@RequestMapping("/api/collections")
@CrossOrigin(origins = "*")
public class CollectionHistoryController {

    private final CollectionHistoryService collectionHistoryService;

    public CollectionHistoryController(
            CollectionHistoryService collectionHistoryService) {
        this.collectionHistoryService = collectionHistoryService;
    }

    // Add collection record
    @PostMapping
    public int addCollection(
            @RequestBody CollectionHistory collection) {
        return collectionHistoryService.addCollection(collection);
    }

    // Get collection using ID
    @GetMapping("/{id}")
    public CollectionHistory getCollectionById(
            @PathVariable int id) {
        return collectionHistoryService.getCollectionById(id);
    }

    // Get collection using request ID
    @GetMapping("/request/{requestId}")
    public CollectionHistory getCollectionByRequestId(
            @PathVariable int requestId) {
        return collectionHistoryService.getCollectionByRequestId(requestId);
    }

    // Get all collection records
    @GetMapping
    public List<CollectionHistory> getAllCollections() {
        return collectionHistoryService.getAllCollections();
    }

    // Get collections belonging to a particular donor
    @GetMapping("/donor/{donorId}")
    public List<CollectionHistory> getCollectionsByDonorId(
            @PathVariable int donorId) {

        return collectionHistoryService.getCollectionsByDonorId(donorId);
    }
    // Get collections belonging to a particular receiver
@GetMapping("/receiver/{receiverId}")
public List<CollectionHistory> getCollectionsByReceiverId(
        @PathVariable int receiverId) {

    return collectionHistoryService.getCollectionsByReceiverId(receiverId);
}

    // Delete collection record
    @DeleteMapping("/{id}")
    public boolean deleteCollection(@PathVariable int id) {
        return collectionHistoryService.deleteCollection(id);
    }
}
