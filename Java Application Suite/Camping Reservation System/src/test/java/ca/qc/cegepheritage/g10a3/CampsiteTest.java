package ca.qc.cegepheritage.g10a3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampsiteTest {

    @Test
    void testCampsiteConstructorAndGetters() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);

        assertEquals("CS001", campsite.getCampsiteId());
        assertEquals("CG001", campsite.getCampgroundId());
        assertEquals("River Site A", campsite.getCampsiteName());
        assertEquals(4, campsite.getCampsiteMaxPartySize());
    }

    @Test
    void testIsValidPartySizeValid() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);

        assertTrue(campsite.isValidPartySize(1));
        assertTrue(campsite.isValidPartySize(4));
        assertTrue(campsite.isValidPartySize(2));
    }

    @Test
    void testIsValidPartySizeInvalid() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);

        assertFalse(campsite.isValidPartySize(5));
        assertFalse(campsite.isValidPartySize(10));
    }

    @Test
    void testDisplay() {
        Campsite campsite = new Campsite("CS001", "CG001", "River Site A", 4);
        String display = campsite.display();

        assertTrue(display.contains("CS001"));
        assertTrue(display.contains("CG001"));
        assertTrue(display.contains("River Site A"));
        assertTrue(display.contains("4"));
    }


}