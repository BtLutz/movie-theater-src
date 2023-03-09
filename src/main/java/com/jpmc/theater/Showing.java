package com.jpmc.theater;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class Showing {

  Movie movie;
  LocalDateTime startTime;

  public double calculateFee(int audienceCount, int sequence) {
    return movie.calculateTicketPrice(sequence, startTime) * audienceCount;
  }
}
