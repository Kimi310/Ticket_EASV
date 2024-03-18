package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEventViewController {
    public TextField eventNameField;
    private EventCoordinatorViewController eventCoordinatorController;
    public void confirmCreateEventBtn(ActionEvent actionEvent) {
        String name = eventNameField.getText();

        //update/add event properties on the EventCoordinator class
        eventCoordinatorController.updateEventProperties(name);

        //close the CreateEvent Window
        ((Stage) eventNameField.getScene().getWindow()).close();
    }

    public void cancelCreateEventBtn(ActionEvent actionEvent) {
    }

    public void dateNewEventBtn(ActionEvent actionEvent) {
    }





    public void setEventCoordinatorController(EventCoordinatorViewController eventCoordinatorController) {
        this.eventCoordinatorController = eventCoordinatorController;
    }
}
