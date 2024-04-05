package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GenerateTicketController {

    public TextField nameField, amountField, emailField;
    private EventCoordinatorViewController eventCoordinatorViewController;
    private Stage stage;
    private TicketNController  ticketNController;

    public void setEventCoordinatorController(EventCoordinatorViewController eventCoordinatorViewController) {
        this.eventCoordinatorViewController = eventCoordinatorViewController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void generateTicket(ActionEvent actionEvent) {
        String ticketName = nameField.getText();
        String ticketEmail = emailField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TicketN.fxml"));
        Parent root;
        try {
            root = loader.load();
            TicketNController ticketNController = loader.getController();
            ticketNController.setGenerateTicketController(this);
            Stage stage = new Stage();
            stage.setTitle("New Ticket:");
            stage.setScene(new Scene(root));
            ticketNController.setStage(stage);
            stage.show();

            ticketNController.setNewTicket(ticketName, ticketEmail);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ((Stage) nameField.getScene().getWindow()).close();

    }


}
