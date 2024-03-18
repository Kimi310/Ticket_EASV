package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEventViewController {
    public TextField eventNameTF, startingTimeTF, eventLocationTF, otherInfoTF, howToArriveTF;
    public DatePicker endingDateEventButton, startingDateEventButton;

    private EventCoordinatorViewController eventCoordinatorController;
    public void confirmCreateEventBtn(ActionEvent actionEvent) {
        String name = eventNameTF.getText();
        String time = startingTimeTF.getText() + startingDateEventButton.toString();
        String location = eventLocationTF.getText();
        String notes = otherInfoTF.getText();
        String endDate = endingDateEventButton.toString();
        String locationGuidance = howToArriveTF.getText();

        //update/add event properties on the EventCoordinator class
        eventCoordinatorController.updateEventProperties(name, time, location, notes, endDate, locationGuidance);

        //close the CreateEvent Window
        ((Stage) eventNameTF.getScene().getWindow()).close();
    }

    public void cancelCreateEventBtn(ActionEvent actionEvent) {
    }

    public void dateNewEventBtn(ActionEvent actionEvent) {
    }

    public void setEventCoordinatorController(EventCoordinatorViewController eventCoordinatorController) {
        this.eventCoordinatorController = eventCoordinatorController;
    }
}
