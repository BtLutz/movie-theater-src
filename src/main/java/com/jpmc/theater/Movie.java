package com.jpmc.theater;

import lombok.Value;

import javax.money.MonetaryAmount;
import java.time.Duration;

@Value
public class Movie {
    String title;
    Duration runningTime;
    MonetaryAmount ticketPrice;
    boolean isSpecialMovie;

    public Movie(String title, Duration runningTime, MonetaryAmount ticketPrice, boolean isSpecialMovie) {
        if (ticketPrice.getNumber().getScale() > 2) {
            throw new IllegalArgumentException("A ticket price cannot have more than two decimal places.");
        }
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecialMovie = isSpecialMovie;
    }
}
