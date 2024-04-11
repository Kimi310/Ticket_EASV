package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class CreateNewCustomerController {
    public TextField customerNameBT, customerLastNameBT, customerEmailBT;
    public DatePicker customerBirthDateBT;
    public Button confirmCreateCustomerBT;

    public CustomerForEventViewController customerForEventController;

    public Stage stage;


    public void confirmCreateCustomerButton(ActionEvent actionEvent) {
        String customerName = customerNameBT.getText();
        String customerLastname = customerLastNameBT.getText();
        String customerEmail = customerEmailBT.getText();
        String customerBirthDate = customerBirthDateBT.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));


        //Add Customer properties on the Events
        customerForEventController.addCustomerToEvent(customerName, customerLastname, customerBirthDate, customerEmail);

        //Close the Add Customer Window
        ((Stage) customerNameBT.getScene().getWindow()).close();
    }

    public void cancelCreateCustomerButton(ActionEvent actionEvent) {
        ((Stage) customerNameBT.getScene().getWindow()).close();
    }


    public void setCustomerForEventController(CustomerForEventViewController customerForEventController) {
        this.customerForEventController = customerForEventController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

