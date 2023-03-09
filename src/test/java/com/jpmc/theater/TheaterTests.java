package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import javax.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {

  @Test
  void testReserveWithSpecialDiscount() {
    var theater = new Theater(LocalDate.of(2023, 1, 1));
    var john = new Customer("John Doe");
    var audienceCount = 4;
    var sequence = 5;
    var reservation = theater.reserve(john, sequence, 4);
    assertEquals(john, reservation.getCustomer());
    assertEquals(audienceCount, reservation.getAudienceCount());
    assertEquals(sequence, reservation.getSequence());
    assertEquals(Money.of(40, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @Test
  void testReserveWithMatineeDiscount() {
    var theater = new Theater(LocalDate.of(2023, 1, 1));
    var john = new Customer("John Doe");
    var reservation = theater.reserve(john, 2, 4);
    assertEquals(Money.of(37.5, Theater.CURRENCY_UNIT), reservation.totalFee());
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 0, 10})
  void testReserveWithInvalidSequence(int sequence) {
    var theater = new Theater(LocalDate.of(2023, 1, 1));
    var john = new Customer("John Doe");
    assertThrows(IllegalArgumentException.class, () -> theater.reserve(john, sequence, 4));
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 0})
  void testReserveWithInvalidAudienceCount(int audienceCount) {
    var theater = new Theater(LocalDate.of(2023, 1, 1));
    var john = new Customer("John Doe");
    assertThrows(IllegalArgumentException.class, () -> theater.reserve(john, 1, audienceCount));
  }

  @Test
  void printMovieSchedule() {
    Theater theater = new Theater(LocalDate.of(2023, 1, 1));
    theater.printSchedule();
  }

  @ParameterizedTest
  @JsonFileSource(resources = "/schedule.json")
  void testGetJsonSchedule(JsonObject schedule) throws JsonProcessingException {
    var theater = new Theater(LocalDate.of(2023, 1, 1));
    var jsonNode = new ObjectMapper().readTree(schedule.toString());
    assertEquals(jsonNode, theater.getJsonSchedule());
  }
}
