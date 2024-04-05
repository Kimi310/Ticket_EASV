package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class GenerateTicketController {

    private EventCoordinatorViewController eventCoordinatorViewController;
    private Stage stage;

    public void setEventCoordinatorController(EventCoordinatorViewController eventCoordinatorViewController) {
        this.eventCoordinatorViewController = eventCoordinatorViewController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void generateTicket(ActionEvent actionEvent) {
    }
}
