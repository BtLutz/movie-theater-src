package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import lombok.Value;

@Value
public class Movie {

  String title;
  Duration runningTime;
  double ticketPrice;
  boolean isSpecial;

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

  private boolean isMatinee(LocalDateTime startTime) {
    var localTime = startTime.toLocalTime();
    return !localTime.isBefore(LocalTime.of(11, 0)) && !localTime.isAfter(LocalTime.of(16, 0));
  }
}