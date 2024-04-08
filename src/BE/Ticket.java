package BE;

public class Ticket {

    private String serialNumber;
    private String ticketName;
    private String ticketEmail;
    private String ticketPrice;


    public Ticket(String ticketName, String ticketEmail, String ticketPrice, String serialNumber) {
        this.ticketName = ticketName;
        this.ticketEmail = ticketEmail;
        this.ticketPrice = ticketPrice;
        this.serialNumber = serialNumber;
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
}
