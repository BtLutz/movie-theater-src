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
    return Collections.max(List.of(getSpecialDiscount(), getSequenceDiscount(sequence),
        getMatineeDiscount(startTime), getDayDiscount(startTime)));
  }

  private double getMatineeDiscount(LocalDateTime startTime) {
    return isMatinee(startTime) ? ticketPrice * 0.25 : 0;
  }

  private static double getSequenceDiscount(int sequence) {
    switch (sequence) {
      case 1:
        return 3;
      case 2:
        return 2;
      default:
        return 0;
    }
  }

  private double getSpecialDiscount() {
    return isSpecial ? ticketPrice * 0.2 : 0;
  }

  private double getDayDiscount(LocalDateTime startTime) {
    return startTime.getDayOfMonth() == 7 ? 1 : 0;
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