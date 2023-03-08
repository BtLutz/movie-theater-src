package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void testCalculateTicketPriceWithSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.now());
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculateTicketPriceWithFirstShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.now());
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculateTicketPriceWithSecondShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
        assertEquals(10.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculateTicketPriceWithFirstShowingSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.now());
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculateTicketPriceWithSecondShowingSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.now());
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculateTicketPriceWithFirstShowingMatinee() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        assertEquals(9.38, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculatePriceWithSecondShowingMatinee() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        assertEquals(9.38, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculatePriceWithMatinee() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        assertEquals(9.38, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculatePriceWithMatineeSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        assertEquals(9.38, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculatePriceWithShowingBefore11() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 59)));
        assertEquals(12.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testCalculatePriceWithShowingAfter4() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(4, 1)));
        assertEquals(12.5, spiderMan.calculateTicketPrice(showing));
    }
}
