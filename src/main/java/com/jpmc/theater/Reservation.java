package com.jpmc.theater;

public class Reservation {
    private final Customer customer;


    private final Showing showing;
    private final int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        if (audienceCount < 1) {
            throw new IllegalArgumentException("audienceCount must be greater than zero.");
        }
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public double totalFee() {
        return showing.calculateFee(audienceCount);
    }

    public Customer getCustomer() {
        return customer;
    }
    public int getAudienceCount() {
        return audienceCount;
    }
    public Showing getShowing() {
        return showing;
    }
}
