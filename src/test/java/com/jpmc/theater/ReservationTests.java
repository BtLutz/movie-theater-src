package com.jpmc.theater;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.money.Monetary;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationTests {
    @Test
    void testGetTotalFee() {
        var customer = new Customer("John Doe");
        var currency = Monetary.getCurrency(Locale.US);
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, currency), true);
        var showing = new Showing(movie, 1, LocalDateTime.now());
        assertEquals(Money.of(37.5, currency), new Reservation(customer, showing, 3).getTotalFee());
    }

    @Test
    void testReservation() {
        var customer = new Customer("John Doe");
        var movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, Monetary.getCurrency(Locale.US)), true);
        var showing = new Showing(movie, 1, LocalDateTime.now());
        var reservation = new Reservation(customer, showing, 3);
        assertEquals(customer, reservation.getCustomer());
        assertEquals(3, reservation.getAudienceCount());
        assertEquals(showing, reservation.getShowing());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void testReservationWithAudienceCountLessThanOne(int audienceCount) {
        var customer = new Customer("John Doe");
        var showing = new Showing(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), Money.of(12.5, Monetary.getCurrency(Locale.US)), true), 1, LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> new Reservation(customer, showing, audienceCount));
    }
}
