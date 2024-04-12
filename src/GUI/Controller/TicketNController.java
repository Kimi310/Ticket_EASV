package GUI.Controller;

import BE.Ticket;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class TicketNController {
    public Label ticketNamelabel, ticketEmailLabel, serialNumberLabel, ticketPriceLabel, ticketLocationLabel, ticketTimeLabel, ticketEventNameLabel, ticketTypeLabel;


    protected void setNewTicket(String ticketName, String ticketEmail, String ticketPrice, String serialNumber, String eventTime, String eventLocation, String eventName){
        ticketNamelabel.setText(ticketName);
        ticketEmailLabel.setText(ticketEmail);
        ticketPriceLabel.setText(ticketPrice);
        serialNumberLabel.setText(serialNumber);
        ticketTimeLabel.setText(eventTime);
        ticketLocationLabel.setText(eventLocation);
        ticketEventNameLabel.setText(eventName);
    }


}
