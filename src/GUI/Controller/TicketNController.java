package GUI.Controller;

import BE.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class TicketNController {
    public Label ticketNamelabel, ticketEmailLabel, serialNumberLabel, ticketPriceLabel, ticketLocationLabel, ticketTimeLabel;
    public AnchorPane ticketPane;

    private Stage stage;

    private GenerateTicketController generateTicketController;

    protected void setNewTicket(String ticketName, String ticketEmail, String ticketPrice, String serialNumber, String eventTime, String eventLocation){
        Ticket newTicket = new Ticket(ticketName, ticketEmail, ticketPrice, serialNumber);

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




    public void printTicket(ActionEvent actionEvent) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(stage)) {
            boolean success = printerJob.printPage(ticketPane);
            if (success) {
                printerJob.endJob();
            }
        }
    }
}
