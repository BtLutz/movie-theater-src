package com.jpmc.theater;

import javax.money.MonetaryAmount;

public class Reservation {

    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        if (audienceCount < 1) {
            throw new IllegalArgumentException("Expected audienceCount to be greater than zero, but got " + audienceCount);
        }
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public MonetaryAmount getTotalFee() {
        return showing.getMovie().getTicketPrice().multiply(audienceCount);
    }
}