package BE;

public class Ticket {

    private String serialNumber;
    private String ticketName;
    private String ticketEmail;
    private String ticketPrice;
    private String eventTime;
    private String eventLocation;
    private String eventName;
    private String seat;


    public Ticket(String ticketName, String ticketEmail, String ticketPrice, String serialNumber) {
        this.ticketName = ticketName;
        this.ticketEmail = ticketEmail;
        this.ticketPrice = ticketPrice;
        this.serialNumber = serialNumber;
    }

    public Ticket(String ticketName, String ticketEmail, String ticketPrice, String serialNumber, String eventTime, String eventLocation, String eventName, String seat) {
        this.ticketName = ticketName;
        this.ticketEmail = ticketEmail;
        this.ticketPrice = ticketPrice;
        this.serialNumber = serialNumber;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventName = eventName;
        this.seat = seat;
    }


    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketEmail() {
        return ticketEmail;
    }

    public void setTicketEmail(String ticketEmail) {
        this.ticketEmail = ticketEmail;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String evenTime) {
        this.eventTime = evenTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
