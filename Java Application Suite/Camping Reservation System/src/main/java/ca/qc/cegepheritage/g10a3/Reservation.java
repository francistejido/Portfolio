package ca.qc.cegepheritage.g10a3;

import java.time.LocalDate;

public class Reservation {
    private String reservationId;
    private String campgroundId;
    private String campsiteId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int partySize;
    private double totalPrice;

    public Reservation(String reservationId, String campgroundId, String campsiteId, LocalDate startDate, LocalDate endDate, int partySize, double totalPrice) {
        this.reservationId = reservationId;
        this.campgroundId = campgroundId;
        this.campsiteId = campsiteId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.partySize = partySize;
        this.totalPrice = totalPrice;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCampgroundId() {
        return campgroundId;
    }

    public String getCampsiteId() {
        return campsiteId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getPartySize() {
        return partySize;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String display() {
        return String.format("Reservation ID: %s | Campground ID: %s | Campsite ID: %s | Start Date: %s | End Date: %s | Party Size: %d | Total Price: $%.2f",
                reservationId, campgroundId, campsiteId, startDate, endDate, partySize, totalPrice);
    }
}
