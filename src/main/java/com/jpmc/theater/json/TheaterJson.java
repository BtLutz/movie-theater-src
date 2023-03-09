package com.jpmc.theater.json;

import com.jpmc.theater.Showing;
import com.jpmc.theater.Theater;
import java.util.ArrayList;
import java.util.List;

public class TheaterJson {

  private final String localDate;

  private final List<ShowingJson> schedule;

  public TheaterJson(Theater theater) {
    this.localDate = theater.getLocalDate().toString();
    this.schedule = new ArrayList<>();
    for (int i = 0; i < theater.getSchedule().size(); i++) {
      var showing = theater.getSchedule().get(i);
      var showingJson = new ShowingJson(showing, i + 1);
      this.schedule.add(showingJson);
    }
  }

  public String getLocalDate() {
    return localDate;
  }

  public List<ShowingJson> getSchedule() {
    return schedule;
  }

  public static class ShowingJson {

    private final int sequence;

    private final String startTime;
    private final String title;
    private final String runningTime;
    private final String ticketPrice;

    public ShowingJson(Showing showing, int sequence) {
      var movie = showing.getMovie();
      this.sequence = sequence;
      this.startTime = showing.getStartTime().toString();
      this.title = movie.getTitle();
      this.runningTime = Theater.humanReadableFormat(movie.getRunningTime());
      this.ticketPrice = String.format("$%s", movie.getTicketPrice());
    }

    public int getSequence() {
      return sequence;
    }

    public String getStartTime() {
      return startTime;
    }

    public String getTitle() {
      return title;
    }

    public String getRunningTime() {
      return runningTime;
    }

    public String getTicketPrice() {
      return ticketPrice;
    }

  }
}

