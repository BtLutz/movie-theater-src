package com.jpmc.theater;

public class Reservation {

  private final Customer customer;
  private final Showing showing;
  private final int audienceCount;
  private final int sequence;

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
    return showing.calculateFee(audienceCount, sequence);
  }

  public Customer getCustomer() {
    return customer;
  }

  public int getSequence() {
    return sequence;
  }

  public int getAudienceCount() {
    return audienceCount;
  }

  public Showing getShowing() {
    return showing;
  }
}
