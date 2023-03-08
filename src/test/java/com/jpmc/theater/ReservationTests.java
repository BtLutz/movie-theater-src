package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationTests {
    @Test
    void testCalculateFee() {
        var customer = new Customer("John Doe");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, false),
                3,
                LocalDateTime.now()
        );
        var reservation = new Reservation(customer, showing, 3);
        assertEquals(37.5, reservation.totalFee());
    }
    @Test
    void testCalculateFeeWithFirstSequenceDiscount() {
        var customer = new Customer("John Doe");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true),
                1,
                LocalDateTime.now()
        );
        var reservation = new Reservation(customer, showing, 3);
        assertEquals(28.5, reservation.totalFee());
    }

    @Test
    void testCalculateFeeWithSpecialDiscount() {
        var customer = new Customer("John Doe");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 15.5, true),
                1,
                LocalDateTime.now()
        );
        var reservation = new Reservation(customer, showing, 3);
        assertEquals(37.2, reservation.totalFee());
    }

    @Test
    void testCalculateFeeWithSecondSequenceDiscount() {
        var customer = new Customer("John Doe");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, false),
                2,
                LocalDateTime.now()
        );
        var reservation = new Reservation(customer, showing, 3);
        assertEquals(31.5, reservation.totalFee());
    }
    @Test
    void testReservation() {
        var audienceCount = 3;
        var customer = new Customer("John Doe");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true),
                1,
                LocalDateTime.now()
        );
        var reservation = new Reservation(customer, showing, audienceCount);
        assertEquals(customer, reservation.getCustomer());
        assertEquals(showing, reservation.getShowing());
        assertEquals(audienceCount, reservation.getAudienceCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void testReservationWithAudienceCountLessThanOne(int audienceCount) {
        var customer = new Customer("John Doe");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true),
                1,
                LocalDateTime.now()
        );
        assertThrows(IllegalArgumentException.class, () -> new Reservation(customer, showing, audienceCount));
    }
}
