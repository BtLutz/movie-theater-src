package com.jpmc.theater;

import lombok.Value;

import javax.money.MonetaryAmount;

@Value
public class Reservation {
    Customer customer;
    Showing showing;
    int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        if (audienceCount < 1) {
            throw new IllegalArgumentException("Expected audienceCount to be greater than zero, but got " + audienceCount);
        }
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public MonetaryAmount getTotalFee() {
        return showing.getMovie().getTicketPrice().multiply(audienceCount);
    }
}