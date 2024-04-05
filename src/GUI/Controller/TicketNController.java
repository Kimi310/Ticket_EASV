package GUI.Controller;

import BE.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TicketNController {
    public Label ticketNamelabel;
    public Label ticketEmailLabel;

    private Stage stage;

    private GenerateTicketController generateTicketController;

    protected void setNewTicket(String ticketName, String ticketEmail){
        Ticket newTicket = new Ticket(ticketName, ticketEmail);

        ticketNamelabel.setText(ticketName);
        ticketEmailLabel.setText(ticketEmail);

    }

    public void setGenerateTicketController(GenerateTicketController generateTicketController){
        this.generateTicketController = generateTicketController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
