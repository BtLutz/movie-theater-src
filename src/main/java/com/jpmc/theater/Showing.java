package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {

  private final Movie movie;
  private final LocalDateTime startTime;

  public Showing(Movie movie, LocalDateTime startTime) {
    this.movie = movie;
    this.startTime = startTime;
  }

  public Movie getMovie() {
    return movie;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public double calculateFee(int audienceCount, int sequence) {
    return movie.calculateTicketPrice(sequence, startTime) * audienceCount;
  }
}
