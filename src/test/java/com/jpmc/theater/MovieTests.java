package com.jpmc.theater;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;
import java.time.Duration;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTests {
    @Test
    void testEqualsWithEqualMovie() {
        var movie1 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        var movie2 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        assertEquals(movie1, movie2);
    }

    @Test
    void testEqualsWithDifferentTicketPrice() {
        var movie1 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        var movie2 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(9, Monetary.getCurrency(Locale.US)), false);
        assertNotEquals(movie1, movie2);
    }

    @Test
    void testEqualsWithDifferentTitle() {
        var movie1 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        var movie2 = new Movie("Superbad II: Revenge of The Nerds", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        assertNotEquals(movie1, movie2);
    }

    @Test
    void testEqualsWithDifferentRunningTime() {
        var movie1 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        var movie2 = new Movie("Superbad", Duration.ofMinutes(91), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        assertNotEquals(movie1, movie2);
    }

    @Test
    void testEqualsWithDifferentIsSpecialMovie() {
        var movie1 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), false);
        var movie2 = new Movie("Superbad", Duration.ofMinutes(90), Money.of(10, Monetary.getCurrency(Locale.US)), true);
        assertNotEquals(movie1, movie2);
    }

    @Test
    void testMovieWithUnRoundedTicketPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Movie("Superbad", Duration.ofMinutes(90), Money.of(10.001, Monetary.getCurrency(Locale.US)), false));
    }
}
