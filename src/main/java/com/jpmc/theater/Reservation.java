package com.jpmc.theater;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Value;

@Value
public class Reservation {

  Customer customer;
  Showing showing;
  int audienceCount;
  int sequence;

  public Reservation(Customer customer, Showing showing, int audienceCount, int sequence) {
    if (audienceCount < 1) {
      throw new IllegalArgumentException("audienceCount must be greater than zero.");
    }
    this.customer = customer;
    this.showing = showing;
    this.audienceCount = audienceCount;
    this.sequence = sequence;
  }

  public double totalFee() {
    var totalFee = showing.calculateFee(audienceCount, sequence);
    return BigDecimal.valueOf(totalFee).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
