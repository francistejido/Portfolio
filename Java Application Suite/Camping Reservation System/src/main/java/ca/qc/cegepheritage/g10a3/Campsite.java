package ca.qc.cegepheritage.g10a3;

public class Campsite {
    private String campsiteId;
    private String campgroundId;
    private String campsiteName;
    private int campsiteMaxPartySize;

    public Campsite( String campsiteId, String campgroundId, String campsiteName, int campsiteMaxPartySize){
        this.campsiteId = campsiteId;
        this.campgroundId = campgroundId;
        this.campsiteName = campsiteName;
        this.campsiteMaxPartySize = campsiteMaxPartySize;
    }

    public String getCampsiteId() {
        return campsiteId;
    }

    public String getCampgroundId() {
        return campgroundId;
    }

    public String getCampsiteName() {
        return campsiteName;
    }

    public int getCampsiteMaxPartySize() {
        return campsiteMaxPartySize;
    }

    public boolean isValidPartySize(int partySize) {
        return partySize <= campsiteMaxPartySize;
    }

    public String display() {
        return String.format("Campsite ID: %s | Campground ID: %s | Name: %s | Max party size: %d",
                campsiteId, campgroundId, campsiteName, campsiteMaxPartySize);
    }
}
