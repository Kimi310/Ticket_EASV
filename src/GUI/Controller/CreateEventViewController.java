package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEventViewController {
    public TextField eventNameTF;
    private EventCoordinatorViewController eventCoordinatorController;
    public void confirmCreateEventBtn(ActionEvent actionEvent) {
        String name = eventNameTF.getText();

        //update/add event properties on the EventCoordinator class
        eventCoordinatorController.updateEventProperties(name);

        //close the CreateEvent Window
        ((Stage) eventNameTF.getScene().getWindow()).close();
    }

    public void cancelCreateEventBtn(ActionEvent actionEvent) {
        ((Stage) eventNameTF.getScene().getWindow()).close();
    }


    public void setEventCoordinatorController(EventCoordinatorViewController eventCoordinatorController) {
        this.eventCoordinatorController = eventCoordinatorController;
    }

    public void startingDateEventBtn(ActionEvent actionEvent) {
    }


    public void endingDateEventBtn(ActionEvent actionEvent) {
    }


}
