package com.jpmc.theater;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.json.TheaterJson;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

  private final List<Showing> schedule;

  private final LocalDate localDate;

  public Theater(LocalDate localDate) {
    this.localDate = localDate;

    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true);
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
    Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, false);
    schedule = List.of(
        new Showing(turningRed, LocalDateTime.of(localDate, LocalTime.of(9, 0))),
        new Showing(spiderMan, LocalDateTime.of(localDate, LocalTime.of(11, 0))),
        new Showing(theBatMan, LocalDateTime.of(localDate, LocalTime.of(12, 50))),
        new Showing(turningRed, LocalDateTime.of(localDate, LocalTime.of(14, 30))),
        new Showing(spiderMan, LocalDateTime.of(localDate, LocalTime.of(16, 10))),
        new Showing(theBatMan, LocalDateTime.of(localDate, LocalTime.of(17, 50))),
        new Showing(turningRed, LocalDateTime.of(localDate, LocalTime.of(19, 30))),
        new Showing(spiderMan, LocalDateTime.of(localDate, LocalTime.of(21, 10))),
        new Showing(theBatMan, LocalDateTime.of(localDate, LocalTime.of(23, 0)))
    );
  }

  public Reservation reserve(Customer customer, int sequence, int audienceCount) {
    Showing showing;
    try {
      showing = schedule.get(sequence - 1);
    } catch (ArrayIndexOutOfBoundsException ex) {
      throw new IllegalArgumentException(
          "not able to find any showing for given sequence " + sequence);
    }
    return new Reservation(customer, showing, audienceCount, sequence);
  }

  public void printSchedule() {
    System.out.println(localDate);
    System.out.println("===================================================");
    for (int i = 0; i < schedule.size(); i++) {
      var showing = schedule.get(i);
      var movie = showing.getMovie();
      System.out.printf("%s: %s %s (%s) $%s\n", i + 1, showing.getStartTime(),
          movie.getTitle(),
          humanReadableFormat(movie.getRunningTime()),
          movie.getTicketPrice());
    }
    System.out.println("===================================================");
  }

  public JsonNode getJsonSchedule() {
    var theaterJson = new TheaterJson(this);
    return new ObjectMapper().registerModule(new JavaTimeModule()).valueToTree(theaterJson);
  }

  public static String humanReadableFormat(Duration duration) {
    long hour = duration.toHours();
    long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

    return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin,
        handlePlural(remainingMin));
  }

  public static String handlePlural(long value) {
    return value == 1 ? "" : "s";
  }

  public List<Showing> getSchedule() {
    return schedule;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public static void main(String[] args) {
    Theater theater = new Theater(LocalDate.now());
    theater.printSchedule();
  }
}
