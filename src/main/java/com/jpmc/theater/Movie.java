package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
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

  public double calculateTicketPrice(int sequence, LocalDateTime startTime) {
    return ticketPrice - getDiscount(sequence, startTime);
  }

  private double getDiscount(int sequence, LocalDateTime startTime) {
    double specialDiscount = 0;
    if (isSpecial) {
      specialDiscount = ticketPrice * 0.2;
    }

    double sequenceDiscount = 0;
    if (sequence == 1) {
      sequenceDiscount = 3;
    } else if (sequence == 2) {

      sequenceDiscount = 2;
    }

    double matineeDiscount = 0;
    if (isMatinee(startTime)) {
      matineeDiscount = ticketPrice * 0.25;
    }

    return Collections.max(List.of(specialDiscount, sequenceDiscount, matineeDiscount));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movie movie = (Movie) o;
    return Double.compare(movie.ticketPrice, ticketPrice) == 0 && Objects.equals(title, movie.title)
        && Objects.equals(runningTime, movie.runningTime) && Objects.equals(isSpecial,
        movie.isSpecial);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, runningTime, ticketPrice, isSpecial);
  }

  private boolean isMatinee(LocalDateTime startTime) {
    var localTime = startTime.toLocalTime();
    return !localTime.isBefore(LocalTime.of(11, 0)) && !localTime.isAfter(LocalTime.of(16, 0));
  }
}