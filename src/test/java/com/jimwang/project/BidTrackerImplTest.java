package com.jimwang.project;


import com.jimwang.project.auction.BidTrackerImpl;
import com.jimwang.project.auction.model.BidTracker;
import com.jimwang.project.auction.model.Item;
import com.jimwang.project.auction.model.User;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BidTrackerImplTest {
    private static final Logger logger = LoggerFactory.getLogger(BidTracker.class);

    @Test
    public void recordBidTest() {
        logger.info("In recordBidTest");

        Item iphone = new Item("iPhone 6");
        Item galaxy = new Item("Galaxy S6");

        User jim = new User("Jim");
        User jimsBrother = new User("Jim's Brother");

        BidTracker bidTracker = new BidTrackerImpl();
        String jimsBidId = bidTracker.recordBid(jim, iphone, 100, "bid 1");
        bidTracker.recordBid(jimsBrother, iphone, 99, "bid 2");
        bidTracker.recordBid(jim, galaxy, 50, "bid 3");

        assertThat(bidTracker.getWinningBid(iphone).id, is(jimsBidId));
        assertThat(bidTracker.getWinningBid(iphone).price, is(100));

        assertThat(bidTracker.getAllBids(iphone).size(), is(2));

        assertThat(bidTracker.getBidItems(jimsBrother).size(), is(1));
        assertThat(bidTracker.getBidItems(jimsBrother), not(hasItem(galaxy)));
        assertThat(bidTracker.getBidItems(jim), allOf(hasItem(iphone), hasItem(galaxy)));
    }

}
