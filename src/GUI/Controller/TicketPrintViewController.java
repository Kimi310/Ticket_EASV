package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void setTicketPropertiesStanding(String ticketName, String ticketEmail, String ticketPrice, String serialNumber, String eventTime, String eventLocation, String eventName){
       ticketNController.setNewTicket(ticketName,ticketEmail,ticketPrice,serialNumber,eventTime,eventLocation,eventName);
       ticketNController.seatLabel.setVisible(false);
    }
    public void setTicketPropertiesSeated(String ticketName, String ticketEmail, String ticketPrice, String serialNumber, String eventTime, String eventLocation, String eventName, String seat){
        ticketNController.setNewTicket(ticketName,ticketEmail,ticketPrice,serialNumber,eventTime,eventLocation,eventName);
        ticketNController.setSeat(seat);
    }

    @FXML
    public void initialize(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TicketN.fxml"));
        try {
            ticketNHbox.getChildren().add(loader.load());
            ticketNController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
