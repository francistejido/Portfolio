package ca.qc.cegepheritage.g10a3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampgroundTest {

    @Test
    void testCampgroundConstructorAndGetters() {
        Campground campground = new Campground("CG001", "Riverside Camping", 45.00, 14);

        assertEquals("CG001", campground.getCampgroundId());
        assertEquals("Riverside Camping", campground.getCampgroundName());
        assertEquals(45.00, campground.getCostPerNight(), 0.01);
        assertEquals(14, campground.getMaxNumberOfNights());
    }

    @Test
    void testIsValidNumberOfNightsValid() {
        Campground campground = new Campground("CG001", "Riverside Camping", 45.00, 14);

        assertTrue(campground.isValidNumberOfNights(5));
        assertTrue(campground.isValidNumberOfNights(14));
        assertTrue(campground.isValidNumberOfNights(1));
    }

    @Test
    void testIsValidNumberOfNightsInvalid() {
        Campground campground = new Campground("CG001", "Riverside Camping", 45.00, 14);

        assertFalse(campground.isValidNumberOfNights(15));
        assertFalse(campground.isValidNumberOfNights(100));
    }

    @Test
    void testDisplay() {
        Campground campground = new Campground("CG001", "Riverside Camping", 45.00, 14);
        String display = campground.display();

        assertTrue(display.contains("CG001"));
        assertTrue(display.contains("Riverside Camping"));
        assertTrue(display.contains("45.00"));
        assertTrue(display.contains("14"));
    }

}