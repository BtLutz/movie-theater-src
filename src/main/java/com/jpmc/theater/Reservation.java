package com.jpmc.theater;

import java.math.RoundingMode;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.RoundingQueryBuilder;
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

  public MonetaryAmount totalFee() {
    var monetaryRounding = Monetary.getRounding(
        RoundingQueryBuilder.of().setScale(2).set(RoundingMode.HALF_UP).build());
    return showing.calculateFee(audienceCount, sequence).with(monetaryRounding);
  }
}
