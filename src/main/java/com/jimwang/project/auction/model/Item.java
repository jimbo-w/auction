package com.jimwang.project.auction.model;

import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * The main data structure underlying Item
 * is a ConcurrentSkipListSet which maintains a naturally
 * ordered set of Bids, based on bid price.
 *
 * Given immutability of the field: id, the thread-safe
 * data structure: ConcurrentSkipListSet and immutability of Bid objects
 * we assert this class is thread-safe.
 *
 * ConcurrentSkipListSet gives add and get complexity of O(log n), however
 * complexity of getting the winning bid is O(1).  We
 * assert here, there will be more access of getWinningBid than
 * addBid or removeBid
 *
 * If used as a listing, here we would record other data of the listing
 * i.e. currency, start and end time, etc
 */
public class Item {
    private final String id;
    private final SortedSet<Bid> bids = new ConcurrentSkipListSet<>();

    public Item(String id) {
        this.id = id;
    }

    public void addBid(Bid bid){
        bids.add(bid); // O(log n)
    }

    public SortedSet<Bid> getBids() {
        return bids;
    }

    @Override
    public String toString() {
        return id;
    }
}
