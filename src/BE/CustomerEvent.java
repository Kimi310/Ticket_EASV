package BE;

public class CustomerEvent {
    private int customerID;
    private int eventID;
    private int id;

    public CustomerEvent(int customerID, int eventID, int id) {
        this.customerID = customerID;
        this.eventID = eventID;
        this.id = id;
    }

    public void setcustomerID(int customerD) {
        this.customerID = customerID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getcustomerID() {
        return customerID;
    }

    public int getEventID() {
        return eventID;
    }

    public int getId() {
        return id;
    }
}
