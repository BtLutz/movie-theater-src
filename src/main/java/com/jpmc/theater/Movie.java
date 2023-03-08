package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private final String title;
    private final Duration runningTime;
    private final double ticketPrice;
    private final boolean isSpecial;

    public Movie(String title, Duration runningTime, double ticketPrice, boolean isSpecial) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecial = isSpecial;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay());
    }

    private double getDiscount(int showSequence) {
        double specialDiscount = 0;
        if (isSpecial) {
            specialDiscount = ticketPrice * 0.2;
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3;
        } else if (showSequence == 2) {

            sequenceDiscount = 2;
        }
        return Math.max(specialDiscount, sequenceDiscount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(isSpecial, movie.isSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, isSpecial);
    }
}