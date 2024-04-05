package BE;

public class Ticket {

    private int serialNumber;
    private String ticketName;
    private String ticketEmail;

    public Ticket(String ticketName, String ticketEmail){
        this.ticketName = ticketName;
        this.ticketEmail = ticketEmail;
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
}
