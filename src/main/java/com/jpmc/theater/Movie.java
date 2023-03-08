package com.jpmc.theater;

import javax.money.MonetaryAmount;
import java.time.Duration;
import java.util.Objects;

public class Movie {
    private final String title;
    private final Duration runningTime;
    private final MonetaryAmount ticketPrice;
    private final boolean isSpecialMovie;

    public Movie(String title, Duration runningTime, MonetaryAmount ticketPrice, boolean isSpecialMovie) {
        if (ticketPrice.getNumber().getScale() > 2) {
            throw new IllegalArgumentException("A ticket price cannot have more than two decimal places.");
        }
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecialMovie = isSpecialMovie;
    }

    public boolean isSpecialMovie() {
        return isSpecialMovie;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public MonetaryAmount getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movie.getTicketPrice().isEqualTo(ticketPrice)
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(isSpecialMovie, movie.isSpecialMovie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, isSpecialMovie);
    }
}
