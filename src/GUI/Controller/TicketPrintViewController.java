package GUI.Controller;

import BE.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TicketPrintViewController {
    @FXML
    public HBox ticketNHbox;
    private GenerateTicketController generateTicketController;
    private Stage stage;
    private TicketNController ticketNController;

    public void setGenerateTicketController(GenerateTicketController generateTicketController) {
        this.generateTicketController = generateTicketController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTickets(List<Ticket> tickets) {
        ticketNHbox.getChildren().clear(); // Clear existing tickets
        for (Ticket ticket : tickets) {
            addTicketToPrint(ticket);
        }
    }

    private void addTicketToPrint(Ticket ticket) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TicketN.fxml"));
        try {
            ticketNHbox.getChildren().add(loader.load());
            ticketNController = loader.getController();
            ticketNController.setNewTicket(ticket.getTicketName(), ticket.getTicketEmail(), ticket.getTicketPrice(), ticket.getSerialNumber(), ticket.getEventTime(), ticket.getEventLocation(), ticket.getEventName());
            if (ticket.getSeat() != null) {
                ticketNController.setSeat(ticket.getSeat());
            } else {
                ticketNController.seatLabel.setVisible(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTicket(ActionEvent actionEvent) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(stage.getOwner())) {
            boolean success = job.printPage(ticketNHbox);
            if (success) {
                job.endJob();
            }
        }
    }
}
