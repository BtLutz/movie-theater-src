package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerTests {
    @Test
    void testEqualsWithEqualCustomer() {
        var customer1 = new Customer("James Dean");
        var customer2 = new Customer("James Dean");
        assertEquals(customer1, customer2);
    }

    @Test
    void testEqualsWithDifferentName() {
        var customer1 = new Customer("James Dean");
        var customer2 = new Customer("Jimmy Dean");
        assertNotEquals(customer1, customer2);
    }
}
