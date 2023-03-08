package com.jpmc.theater;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {

  @Test
  void testCalculateTicketPriceWithIsSpecial() {
    var spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true);
    assertEquals(10, spiderMan.calculateTicketPrice(5, LocalDateTime.of(2023, 1, 1, 0, 0)));
  }
}
