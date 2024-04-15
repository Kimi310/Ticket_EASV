package GUI.Controller;

import BE.Event;
import BE.EventCoordinator;
import BLL.ECEventService;
import BLL.EventCoordinatorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddECToEventController{
    @FXML
    private Label errtxt;
    @FXML
    private TextField logintxt;
    private final ECEventService eventService = new ECEventService();
    private final EventCoordinatorService eventCoordinatorService = new EventCoordinatorService();
    private ArrayList<EventCoordinator> coordinators = eventCoordinatorService.getCoordinators();
    private Event event;
    private ECEventViewController controller;


    @FXML
    private void addEChandle(ActionEvent actionEvent) {
        for (EventCoordinator ec : coordinators) {
            if (Objects.equals(logintxt.getText(), ec.getLogin())) {
                eventService.addECToEvent(ec,event);
                controller.poluteUsers();
                Stage stage = (Stage) logintxt.getScene().getWindow();
                stage.close();
            }
        }
        errtxt.setVisible(true);
        logintxt.setText("");
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setController(ECEventViewController controller) {
        this.controller = controller;
    }
}
