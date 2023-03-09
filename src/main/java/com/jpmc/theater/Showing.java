package com.jpmc.theater;

import java.time.LocalDateTime;
import javax.money.MonetaryAmount;
import lombok.Value;

@Value
public class Showing {

  Movie movie;
  LocalDateTime startTime;

  public MonetaryAmount calculateFee(int audienceCount, int sequence) {
    return movie.calculateTicketPrice(sequence, startTime).multiply(audienceCount);
  }
}
