package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void testReserveWithSpecialDiscount() {
        var theater = new Theater();
        var john = new Customer("John Doe");
        var audienceCount = 4;
        var sequence = 2;
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(john, reservation.getCustomer());
        assertEquals(audienceCount, reservation.getAudienceCount());
        assertEquals(sequence, reservation.getSequence());
        assertEquals(40, reservation.totalFee());
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater();
        theater.printSchedule();
    }
}
