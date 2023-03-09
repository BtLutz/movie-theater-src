package com.jpmc.theater.json;

import com.jpmc.theater.Showing;
import com.jpmc.theater.Theater;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Value;

@Value
public class TheaterJson {

  String localDate;

  List<ShowingJson> schedule;

  public TheaterJson(Theater theater) {
    this.localDate = theater.getLocalDate().toString();
    var showings = theater.getSchedule();
    this.schedule = IntStream.range(0, showings.size())
        .mapToObj((i -> new ShowingJson(showings.get(i), i + 1))).collect(Collectors.toList());
  }

  @Value
  public static class ShowingJson {

    int sequence;

    String startTime;
    String title;
    String runningTime;
    String ticketPrice;

    public ShowingJson(Showing showing, int sequence) {
      var movie = showing.getMovie();
      this.sequence = sequence;
      this.startTime = showing.getStartTime().toString();
      this.title = movie.getTitle();
      this.runningTime = Theater.humanReadableFormat(movie.getRunningTime());
      this.ticketPrice = String.format("$%s", movie.getTicketPrice());
    }
  }
}

