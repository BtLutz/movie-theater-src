package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {

  @Test
  void testReserveWithSpecialDiscount() {
    var theater = new Theater();
    var john = new Customer("John Doe");
    var audienceCount = 4;
    var sequence = 5;
    var reservation = theater.reserve(john, sequence, 4);
    assertEquals(john, reservation.getCustomer());
    assertEquals(audienceCount, reservation.getAudienceCount());
    assertEquals(sequence, reservation.getSequence());
    assertEquals(40, reservation.totalFee());
  }

  @Test
  void testReserveWithMatineeDiscount() {
    var theater = new Theater();
    var john = new Customer("John Doe");
    var reservation = theater.reserve(john, 2, 4);
    assertEquals(37.5, reservation.totalFee());
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 0, 10})
  void testReserveWithInvalidSequence(int sequence) {
    var theater = new Theater();
    var john = new Customer("John Doe");
    assertThrows(IllegalArgumentException.class, () -> theater.reserve(john, sequence, 4));
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 0})
  void testReserveWithInvalidAudienceCount(int audienceCount) {
    var theater = new Theater();
    var john = new Customer("John Doe");
    assertThrows(IllegalArgumentException.class, () -> theater.reserve(john, 1, audienceCount));
  }

  @Test
  void printMovieSchedule() {
    Theater theater = new Theater();
    theater.printSchedule();
  }
}
