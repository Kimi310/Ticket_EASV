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
    private Stage stage;
    private TicketNController ticketNController;
    private List<Ticket> tickets;
    private int currentIndex = 0;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        if (!tickets.isEmpty()) {
            displayTicket(0);
        }
    }

    private void displayTicket(int index) {
        ticketNHbox.getChildren().clear();
        addTicketToPrint(tickets.get(index));
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

    public void printCurrentTicket(ActionEvent actionEvent) {
        if (!tickets.isEmpty()) {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(stage.getOwner())) {
                boolean success = job.printPage(ticketNHbox);
                if (success) {
                    job.endJob();
                }
            }
        }
    }

    public void goToNextTicket(ActionEvent actionEvent) {
        if (!tickets.isEmpty()) {
            currentIndex = (currentIndex + 1) % tickets.size();
            displayTicket(currentIndex);
        }
    }

    public void goToPreviousTicket(ActionEvent actionEvent) {
        if (!tickets.isEmpty()) {
            currentIndex = (currentIndex - 1 + tickets.size()) % tickets.size();
            displayTicket(currentIndex);
        }
    }
}
