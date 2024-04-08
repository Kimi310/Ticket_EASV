package GUI.Controller;

import BE.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TicketNController {
    public Label ticketNamelabel, ticketEmailLabel, serialNumberLabel, ticketPriceLabel, ticketLocationLabel, ticketTimeLabel;

    private Stage stage;

    private GenerateTicketController generateTicketController;

    protected void setNewTicket(String ticketName, String ticketEmail, String ticketPrice, String serialNumber, String eventTime, String eventLocation){
        Ticket newTicket = new Ticket(ticketName, ticketEmail);

        //add ticket to database?

        ticketNamelabel.setText(ticketName);
        ticketEmailLabel.setText(ticketEmail);
        ticketPriceLabel.setText(ticketPrice);
        serialNumberLabel.setText(serialNumber);
        ticketTimeLabel.setText(eventTime);
        ticketLocationLabel.setText(eventLocation);

    }

    public void setGenerateTicketController(GenerateTicketController generateTicketController){
        this.generateTicketController = generateTicketController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
