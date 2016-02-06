package com.jimwang.project.auction.model;

/**
 * All fields (state) are immutable and thus thread-safe.
 * I make them all public here to save on boiler-plate
 * getters and setters.
 */
public class Bid implements Comparable<Bid> {

    public final User user;
    public final Item item;
    public final int price;
    public final String id;

    public Bid(User user, Item item, int price, String id) {
        this.user = user;
        this.item = item;
        this.price = price;
        this.id = id;
    }

    public int compareTo(Bid o) {
        return price - o.price;
    }

}
