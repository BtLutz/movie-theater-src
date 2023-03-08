package com.jpmc.theater;

import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.format.MonetaryFormats;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Theater {

    private final List<Showing> schedule;

    public Theater() {
        var currency = Monetary.getCurrency(Locale.US);
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, currency), true);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), Money.of(11, currency), false);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), Money.of(9, currency), false);
        this.schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)))
        );
    }

    public Reservation reserve(Customer customer, int sequence, int audienceCount) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, audienceCount);
    }

    public void printSchedule() {
        System.out.println(LocalDate.now());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.printf("%s: %s %s %s $%s\n", s.getSequenceOfTheDay(), s.getShowStartTime(), s.getMovie().getTitle(), humanReadableFormat(s.getMovie().getRunningTime()), MonetaryFormats.getAmountFormat(Locale.US).format(s.getMovie().getTicketPrice()))
        );
        System.out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater();
        theater.printSchedule();
    }
}
