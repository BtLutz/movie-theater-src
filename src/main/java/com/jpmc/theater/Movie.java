package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private final String title;
    private final Duration runningTime;
    private final double ticketPrice;
    private final boolean isSpecialMovie;
    public Movie(String title, Duration runningTime, double ticketPrice, boolean isSpecialMovie) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecialMovie = isSpecialMovie;
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
        if (isSpecialMovie) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {

            sequenceDiscount = 2; // $2 discount for 2nd show
        }
//        else {
//            throw new IllegalArgumentException("failed exception");
//        }

        // biggest discount wins
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
                && Objects.equals(isSpecialMovie, movie.isSpecialMovie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, isSpecialMovie);
    }
}
