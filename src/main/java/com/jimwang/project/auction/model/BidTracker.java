package com.jimwang.project.auction.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface BidTracker {

    String recordBid(User user, Item item, int price, String bidId);

    Bid getWinningBid(Item itemId);

    Set<Bid> getAllBids(Item item);

    Collection<Item> getBidItems(User user);

}
