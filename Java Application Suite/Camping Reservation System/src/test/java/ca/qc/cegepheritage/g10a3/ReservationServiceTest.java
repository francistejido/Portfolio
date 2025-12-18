package ca.qc.cegepheritage.g10a3;

import ca.qc.cegepheritage.g10a3.exceptions.DateRangeParseException;
import ca.qc.cegepheritage.g10a3.exceptions.InvalidCapacityException;
import ca.qc.cegepheritage.g10a3.exceptions.MaxStayExceededException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {
    @Test
    void testParseDateIsValid() {
        LocalDate date = ReservationService.parseDate("2025-12-25");
        assertEquals(LocalDate.of(2025, 12, 25), date);
    }

    @Test
    void testParseDateIsInvalidFormat() {
        assertThrows(DateRangeParseException.class, () -> {
            ReservationService.parseDate("25/12/2025");
        });
    }

    @Test
    void testParseDateIsNull() {
        assertThrows(DateRangeParseException.class, () -> {
            ReservationService.parseDate(null);
        });
    }

    @Test
    void testParseDateIsEmpty() {
        assertThrows(DateRangeParseException.class, () -> {
            ReservationService.parseDate("");
        });
    }

    @Test
    void testValidateDateRangeIsValid() {
        LocalDate start = LocalDate.now().plusDays(5);
        LocalDate end = LocalDate.now().plusDays(10);

        assertDoesNotThrow(() -> {
            ReservationService.validateDateRange(start, end);
        });
    }

    @Test
    void testValidateDateRangeIsStartAfterEnd() {
        LocalDate start = LocalDate.now().plusDays(10);
        LocalDate end = LocalDate.now().plusDays(5);

        assertThrows(DateRangeParseException.class, () -> {
            ReservationService.validateDateRange(start, end);
        });
    }

    @Test
    void testValidateDateRangeIsSameDate() {
        LocalDate date = LocalDate.now().plusDays(5);

        assertThrows(DateRangeParseException.class, () -> {
            ReservationService.validateDateRange(date, date);
        });
    }

    @Test
    void testValidateDateRangeIsPastDate() {
        LocalDate start = LocalDate.now().minusDays(5);
        LocalDate end = LocalDate.now().plusDays(5);

        assertThrows(DateRangeParseException.class, () -> {
            ReservationService.validateDateRange(start, end);
        });
    }

    @Test
    void testValidatePartySizeIsValid() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);

        assertDoesNotThrow(() -> {
            ReservationService.validatePartySize(3, campsite);
        });
    }

    @Test
    void testValidatePartySizeIsExceedsCapacity() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);

        assertThrows(InvalidCapacityException.class, () -> {
            ReservationService.validatePartySize(5, campsite);
        });
    }

    @Test
    void testValidatePartySizeIsZero() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);

        assertThrows(InvalidCapacityException.class, () -> {
            ReservationService.validatePartySize(0, campsite);
        });
    }

    @Test
    void testValidatePartySizeIsNegative() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);

        assertThrows(InvalidCapacityException.class, () -> {
            ReservationService.validatePartySize(-1, campsite);
        });
    }

    @Test
    void testCalculateNights() {
        LocalDate start = LocalDate.of(2025, 12, 1);
        LocalDate end = LocalDate.of(2025, 12, 5);

        int nights = ReservationService.calculateNights(start, end);
        assertEquals(4, nights);
    }

    @Test
    void testCalculateNightsIsEqualToOneNight() {
        LocalDate start = LocalDate.of(2025, 12, 1);
        LocalDate end = LocalDate.of(2025, 12, 2);

        int nights = ReservationService.calculateNights(start, end);
        assertEquals(1, nights);
    }

    @Test
    void testCalculateTotalPrice() {
        double total = ReservationService.calculateTotalPrice(5, 45.00);
        assertEquals(225.00, total, 0.01);
    }

    @Test
    void testCalculateTotalPriceOfOneNight() {
        double total = ReservationService.calculateTotalPrice(1, 65.00);
        assertEquals(65.00, total, 0.01);
    }

    @Test
    void testValidateAndCreateRequestIsValid() {
        Campground campground = new Campground("CG001", "Riverside Camping", 45.00, 14);
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);
        LocalDate start = LocalDate.now().plusDays(5);
        LocalDate end = LocalDate.now().plusDays(8);

        assertDoesNotThrow(() -> {
            Reservation reservation = ReservationService.validateAndCreateRequest(campground, campsite, start, end, 3);
            assertNotNull(reservation);
            assertEquals("CG001", reservation.getCampgroundId());
            assertEquals("CS001", reservation.getCampsiteId());
            assertEquals(3, reservation.getPartySize());
        });
    }

    @Test
    void testValidateAndCreateRequestExceedsMaxStay() {
        Campground campground = new Campground("CG002", "Mountain View", 65.00, 7);
        Campsite campsite = new Campsite("CS004", "CG002", "Mountain Peak A", 2);
        LocalDate start = LocalDate.now().plusDays(5);
        LocalDate end = LocalDate.now().plusDays(15); // 10 nights, exceeds 7

        assertThrows(MaxStayExceededException.class, () -> {
            ReservationService.validateAndCreateRequest(campground, campsite, start, end, 2);
        });
    }
}