package com.jpmc.theater;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Test;

import javax.money.Monetary;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
    @Test
    void testReserve() {
        Theater theater = new Theater();
        Customer john = new Customer("John Doe");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(FastMoney.of(50, Monetary.getCurrency(Locale.US)), reservation.getTotalFee());
        assertEquals(john, reservation.getCustomer());
    }

    @Test
    void testReserveWithOutOfBoundsException() {
        Theater theater = new Theater();
        Customer customer = new Customer("James Dean");
        assertThrows(IllegalStateException.class, () -> theater.reserve(customer, 10, 1));
    }
    @Test
    void testPrintMovieSchedule() {
        Theater theater = new Theater();
        theater.printSchedule();
    }
}
