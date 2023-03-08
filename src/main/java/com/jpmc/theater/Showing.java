package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private final Movie movie;
    private final LocalDateTime showStartTime;

    public Showing(Movie movie, LocalDateTime showStartTime) {
        this.movie = movie;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double calculateFee(int audienceCount, int sequence) {
        return movie.calculateTicketPrice(sequence) * audienceCount;
    }
}
