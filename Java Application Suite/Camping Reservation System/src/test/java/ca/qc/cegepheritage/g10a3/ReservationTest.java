package ca.qc.cegepheritage.g10a3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void testReservationConstructorAndGetters() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 5);

        Reservation reservation = new Reservation(
                "RES001", "CG001", "CS001", startDate, endDate, 4, 180.00
        );

        assertEquals("RES001", reservation.getReservationId());
        assertEquals("CG001", reservation.getCampgroundId());
        assertEquals("CS001", reservation.getCampsiteId());
        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
        assertEquals(4, reservation.getPartySize());
        assertEquals(180.00, reservation.getTotalPrice(), 0.01);
    }

    @Test
    void testDisplay() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 5);

        Reservation reservation = new Reservation(
                "RES001", "CG001", "CS001", startDate, endDate, 4, 180.00
        );

        String display = reservation.display();

        assertTrue(display.contains("RES001"));
        assertTrue(display.contains("CG001"));
        assertTrue(display.contains("CS001"));
        assertTrue(display.contains("2025-12-01"));
        assertTrue(display.contains("2025-12-05"));
        assertTrue(display.contains("4"));
        assertTrue(display.contains("180.00"));
    }

}