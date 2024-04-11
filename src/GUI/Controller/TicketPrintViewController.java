package GUI.Controller;

import BE.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicketPrintViewController {
    @FXML
    public HBox ticketNHbox;
    private Stage stage;
    private TicketNController ticketNController;
    private Ticket ticket;
    private int currentIndex = 0;
    private String ticketType;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTicket(Ticket ticket, String ticketType) {
        this.ticket = ticket;
        displayTicket();
        ticketNController.ticketTypeLabel.setText(ticketType);
    }

    private void displayTicket() {
        ticketNHbox.getChildren().clear();
        addTicketToPrint(ticket);
    }

    private void addTicketToPrint(Ticket ticket) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/Ticket.fxml"));
        try {
            ticketNHbox.getChildren().add(loader.load());
            ticketNController = loader.getController();
            ticketNController.setNewTicket(ticket.getTicketName(), ticket.getTicketEmail(), ticket.getTicketPrice(), ticket.getSerialNumber(), ticket.getEventTime(), ticket.getEventLocation(), ticket.getEventName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCurrentTicket(ActionEvent actionEvent) {

            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(stage.getOwner())) {
                boolean success = job.printPage(ticketNHbox);
                if (success) {
                    job.endJob();
                }
            }

    }


}
