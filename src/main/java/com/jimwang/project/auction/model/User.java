package com.jimwang.project.auction.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class User {

    final String id;

    // Access to bids is via thread-safe implementation of Map
    // and Bid is immutable, which makes User thread-safe
    //
    private final Map<String, Bid> bids = new ConcurrentHashMap<>();

    public User(String id) {
        this.id = id;
    }

    public void addBid(Bid bid) {
        bids.put(bid.id, bid);  // O(1)
    }

    public Set<Item> getBidItemIds() {
        return bids.values().stream().map(bid -> bid.item).collect(Collectors.toSet()); // O(n)
    }

    @Override
    public String toString() {
        return id;
    }
}
