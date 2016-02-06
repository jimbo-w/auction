package com.jimwang.project.auction;

import com.jimwang.project.auction.model.Bid;
import com.jimwang.project.auction.model.BidTracker;
import com.jimwang.project.auction.model.Item;
import com.jimwang.project.auction.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * An attempt at a thread-safe implementation.
 *
 * BidTracker simply uses Users and Items to track bids.
 * Item and User should be thread-safe and as BidTracker
 * does not have any state, it should inherit its thread-
 * safety from the objects it uses, namely Item, User
 * and Bid
 *
 */
public class BidTrackerImpl implements BidTracker {
    private static final Logger logger = LoggerFactory.getLogger(BidTrackerImpl.class);

    public String recordBid(User user, Item item, int price, String bidId) {
        logger.debug("Creating bid: {} for user: {} on item: {} at price: {}", bidId, user, item, price);
        Bid bid = new Bid(user, item, price, bidId);
        user.addBid(bid);
        item.addBid(bid);

        return bid.id;
    }

    /**
     * @throws NoSuchElementException when there are no bids on the item
     */
    public Bid getWinningBid(Item item) throws NoSuchElementException {
        return item.getBids().last(); // O(1)
    }

    public Set<Bid> getAllBids(Item item) {
        return item.getBids();
    }

    public Set<Item> getBidItems(User user) {
        return user.getBidItemIds();
    }

}
