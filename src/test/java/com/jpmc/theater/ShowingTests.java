package com.jpmc.theater;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {
    private static final CurrencyUnit CURRENCY_UNIT = Monetary.getCurrency(Locale.US);
    @Test
    void testCalculateTicketPriceWithSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), true);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(2023, 1, 1, 9, 0));
        assertEquals(FastMoney.of(10, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculateTicketPriceWithFirstShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(2023, 1, 1, 9, 0));
        assertEquals(FastMoney.of(9.5, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculateTicketPriceWithSecondShowing() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(2023, 1, 1, 9, 0));
        assertEquals(FastMoney.of(10.5, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculateTicketPriceWithFirstShowingSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), true);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(2023, 1, 1, 9, 0));
        assertEquals(FastMoney.of(9.5, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculateTicketPriceWithSecondShowingSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), true);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(2023, 1, 1, 9, 0));
        assertEquals(FastMoney.of(10, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculateTicketPriceWithFirstShowingMatinee() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)));
        assertEquals(FastMoney.of(9.38, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculatePriceWithSecondShowingMatinee() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)));
        assertEquals(FastMoney.of(9.38, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculatePriceWithMatinee() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(11, 0)));
        assertEquals(FastMoney.of(9.38, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculatePriceWithMatineeSpecialMovie() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), true);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)));
        assertEquals(FastMoney.of(9.38, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculatePriceWithShowingBefore11() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(10, 59)));
        assertEquals(FastMoney.of(12.5, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculatePriceWithShowingAfter4() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(4, 1)));
        assertEquals(FastMoney.of(12.5, CURRENCY_UNIT), showing.calculateTicketPrice());
    }

    @Test
    void testCalculatePriceWithShowingOnSeventh() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), FastMoney.of(12.5, CURRENCY_UNIT), false);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.now()));
        assertEquals(FastMoney.of(11.5, CURRENCY_UNIT), showing.calculateTicketPrice());
    }
}
