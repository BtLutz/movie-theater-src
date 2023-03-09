package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import javax.money.MonetaryAmount;
import lombok.Value;
import org.javamoney.moneta.Money;

@Value
public class Movie {

  String title;
  Duration runningTime;
  MonetaryAmount ticketPrice;
  boolean isSpecial;

  public MonetaryAmount calculateTicketPrice(int sequence, LocalDateTime startTime) {
    return ticketPrice.subtract(getDiscount(sequence, startTime));
  }

  private MonetaryAmount getDiscount(int sequence, LocalDateTime startTime) {
    return Collections.max(
        List.of(getSpecialDiscount(), getSequenceDiscount(sequence), getMatineeDiscount(startTime),
            getDayDiscount(startTime)));
  }

  private MonetaryAmount getMatineeDiscount(LocalDateTime startTime) {
    return isMatinee(startTime) ? ticketPrice.multiply(0.25)
        : Money.zero(ticketPrice.getCurrency());
  }

  private MonetaryAmount getSequenceDiscount(int sequence) {
    switch (sequence) {
      case 1:
        return Money.of(3, ticketPrice.getCurrency());
      case 2:
        return Money.of(2, ticketPrice.getCurrency());
      default:
        return Money.zero(ticketPrice.getCurrency());
    }
  }

  private MonetaryAmount getSpecialDiscount() {
    return isSpecial ? ticketPrice.multiply(0.2) : Money.zero(ticketPrice.getCurrency());
  }

  private MonetaryAmount getDayDiscount(LocalDateTime startTime) {
    var currency = ticketPrice.getCurrency();
    return startTime.getDayOfMonth() == 7 ? Money.of(1, currency) : Money.zero(currency);
  }

  private boolean isMatinee(LocalDateTime startTime) {
    var localTime = startTime.toLocalTime();
    return !localTime.isBefore(LocalTime.of(11, 0)) && !localTime.isAfter(LocalTime.of(16, 0));
  }
}