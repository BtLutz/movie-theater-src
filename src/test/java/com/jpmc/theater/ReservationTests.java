package com.jpmc.theater;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationTests {

  @Test
  void testTotalFee() {
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(12.5, Theater.CURRENCY_UNIT), false), LocalDateTime.of(2023, 1, 1, 0, 0));
    var reservation = new Reservation(customer, showing, 3, 3);
    assertEquals(Money.of(37.5, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @Test
  void testTotalFeeWithFirstSequenceDiscount() {
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(12.5, Theater.CURRENCY_UNIT), true), LocalDateTime.of(2023, 1, 1, 0, 0));
    var reservation = new Reservation(customer, showing, 3, 1);
    assertEquals(Money.of(28.5, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @Test
  void testTotalFeeWithSecondSequenceDiscount() {
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(12.5, Theater.CURRENCY_UNIT), false), LocalDateTime.of(2023, 1, 1, 0, 0));
    var reservation = new Reservation(customer, showing, 3, 2);
    assertEquals(Money.of(31.5, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @Test
  void testTotalFeeWithSpecialDiscount() {
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(15.5, Theater.CURRENCY_UNIT), true), LocalDateTime.of(2023, 1, 1, 0, 0));
    var reservation = new Reservation(customer, showing, 3, 1);
    assertEquals(Money.of(37.2, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @ParameterizedTest
  @ValueSource(ints = {11, 12, 16})
  void testTotalFeeWithMatineeDiscount(int hour) {
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(15.5, Theater.CURRENCY_UNIT), true), LocalDateTime.of(2023, 1, 1, hour, 0));
    var reservation = new Reservation(customer, showing, 3, 1);
    assertEquals(Money.of(34.88, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @Test
  void testTotalFeeWithDayDiscount() {
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(12.5, Theater.CURRENCY_UNIT), false), LocalDateTime.of(2023, 1, 7, 0, 0));
    var reservation = new Reservation(customer, showing, 3, 3);
    assertEquals(Money.of(34.5, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @Test
  void testReservation() {
    var audienceCount = 3;
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(12.5, Theater.CURRENCY_UNIT), true), LocalDateTime.of(2023, 1, 1, 0, 0));
    var reservation = new Reservation(customer, showing, audienceCount, 1);
    assertEquals(customer, reservation.getCustomer());
    assertEquals(showing, reservation.getShowing());
    assertEquals(audienceCount, reservation.getAudienceCount());
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 0})
  void testReservationWithAudienceCountLessThanOne(int audienceCount) {
    var customer = new Customer("John Doe");
    var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(12.5, Theater.CURRENCY_UNIT), true), LocalDateTime.of(2023, 1, 1, 0, 0));
    assertThrows(IllegalArgumentException.class,
        () -> new Reservation(customer, showing, audienceCount, 1));
  }
}
