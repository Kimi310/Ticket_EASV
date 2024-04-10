package BE;

public class UserEvent {
    private int userID;
    private int eventID;
    private int id;

    public UserEvent(int userID, int eventID, int id) {
        this.userID = userID;
        this.eventID = eventID;
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getId() {
        return id;
    }
}
