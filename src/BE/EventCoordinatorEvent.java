package BE;

public class EventCoordinatorEvent {
    private int ECID;
    private int eventID;
    private int id;

    public EventCoordinatorEvent(int id,int ECID, int eventID) {
        this.ECID = ECID;
        this.eventID = eventID;
        this.id = id;
    }

    public int getECID() {
        return ECID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setECID(int ECID) {
        this.ECID = ECID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getId() {
        return id;
    }
}
