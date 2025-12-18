package ca.qc.cegepheritage.g10a3;

public class Campground {
    private String campgroundId;
    private String campgroundName;
    private double costPerNight;
    private int maxNumberOfNights;

    public Campground(String campgroundId, String campgroundName, double costPerNight, int maxNumberOfNights) {
        this.campgroundId = campgroundId;
        this.campgroundName = campgroundName;
        this.costPerNight = costPerNight;
        this.maxNumberOfNights = maxNumberOfNights;
    }

    public String getCampgroundId() {
        return campgroundId;
    }

    public String getCampgroundName() {
        return campgroundName;
    }

    public double getCostPerNight() {
        return costPerNight;
    }

    public int getMaxNumberOfNights() {
        return maxNumberOfNights;
    }

    public boolean isValidNumberOfNights(int numberOfNights) {
        return numberOfNights <= maxNumberOfNights;
    }

    public String display () {
        return String.format("Campground ID: %s | Name: %s | Cost per night: $%.2f | Max nights: %d",
                campgroundId, campgroundName, costPerNight, maxNumberOfNights);
    }
}
