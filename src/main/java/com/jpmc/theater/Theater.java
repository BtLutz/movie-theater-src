package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

  private final List<Showing> schedule;

  public Theater() {
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true);
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
    Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, false);
    schedule = List.of(
        new Showing(turningRed, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))),
        new Showing(spiderMan, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))),
        new Showing(theBatMan, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))),
        new Showing(turningRed, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30))),
        new Showing(spiderMan, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10))),
        new Showing(theBatMan, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50))),
        new Showing(turningRed, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30))),
        new Showing(spiderMan, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10))),
        new Showing(theBatMan, LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)))
    );
  }

  public Reservation reserve(Customer customer, int sequence, int audienceCount) {
    try {
      var showing = schedule.get(sequence - 1);
      return new Reservation(customer, showing, audienceCount, sequence);
    } catch (ArrayIndexOutOfBoundsException ex) {
      throw new IllegalArgumentException(
          "not able to find any showing for given sequence " + sequence);
    }
  }

  public void printSchedule() {
    System.out.println(LocalDate.now());
    System.out.println("===================================================");
    for (int i = 0; i < schedule.size(); i++) {
      var showing = schedule.get(i);
      var movie = showing.getMovie();
      System.out.printf("%s: %s %s %s $%s\n", i + 1, showing.getStartTime(),
          movie.getTitle(),
          humanReadableFormat(movie.getRunningTime()),
          movie.getTicketPrice());
    }
    System.out.println("===================================================");
  }

  public String humanReadableFormat(Duration duration) {
    long hour = duration.toHours();
    long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

    return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin,
        handlePlural(remainingMin));
  }

  // (s) postfix should be added to handle plural correctly
  private String handlePlural(long value) {
    return value == 1 ? "" : "s";
  }

  public static void main(String[] args) {
    Theater theater = new Theater();
    theater.printSchedule();
  }
}
