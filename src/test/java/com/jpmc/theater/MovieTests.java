package com.jpmc.theater;

import java.time.LocalDateTime;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {

  @Test
  void testCalculateTicketPriceWithIsSpecial() {
    var spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),
        Money.of(12.5, Theater.CURRENCY_UNIT), true);
    assertEquals(Money.of(10, Theater.CURRENCY_UNIT),
        spiderMan.calculateTicketPrice(5, LocalDateTime.of(2023, 1, 1, 0, 0)));
  }
}
