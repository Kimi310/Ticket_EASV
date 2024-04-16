package GUI.Controller;

import BLL.EventCoordinatorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEventCoordinatorController {
    @FXML
    private TextField passtxt;
    @FXML
    private Label errtxt;
    @FXML
    private TextField logintxt;
    private AllCoordinatorsController controller;
    private EventCoordinatorService eventCoordinatorService = new EventCoordinatorService();
    @FXML
    private void addEChandle(ActionEvent actionEvent) {
        if (!logintxt.getText().isEmpty() && !passtxt.getText().isEmpty()){
            if (eventCoordinatorService.addCoordinator(logintxt.getText(),passtxt.getText())){
                controller.poluteAllCoordinators();
                Stage stage = (Stage) logintxt.getScene().getWindow();
                stage.close();
            }
        }else {
            errtxt.setVisible(true);
        }
    }

    public void setController(AllCoordinatorsController controller){
        this.controller = controller;
    }
}
