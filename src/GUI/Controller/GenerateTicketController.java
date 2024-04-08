package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.SecureRandom;

public class GenerateTicketController {

    public TextField nameField, amountField, emailField, priceField;
    public ChoiceBox ticketTypeChoiceBox;
    private EventCoordinatorViewController eventCoordinatorViewController;
    private Stage stage;
    private TicketNController  ticketNController;
    private String eventTime, eventLocation, eventName;

    public void setEventCoordinatorController(EventCoordinatorViewController eventCoordinatorViewController) {
        this.eventCoordinatorViewController = eventCoordinatorViewController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void generateTicket(ActionEvent actionEvent) {
        if (!isValidNumber(amountField.getText()) || !isValidNumber(priceField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Amount of tickets and/or price must be numbers");
            alert.showAndWait();
            return;
        }

        String selectedTicketType = (String) ticketTypeChoiceBox.getValue();
        if (selectedTicketType == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a ticket type");
            alert.showAndWait();
            return;
        }

        if (selectedTicketType.equals("Seated Event")) {
            openSeatSelectionWindow();
        } else {
            generateStandingTickets();
        }
    }

    private String generateSerialNumber() {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public void setEventProperties(String time, String location, String name) {
        this.eventTime = time;
        this.eventLocation = location;
        this.eventName = name;
    }

    private boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void openSeatSelectionWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SeatSelectionView.fxml"));
        Parent root;
        try {
            root = loader.load();
            SeatSelectionController seatSelectionController = loader.getController();
            seatSelectionController.setTicketAmount(Integer.parseInt(amountField.getText()));
            Stage stage = new Stage();
            stage.setTitle("Seat Selection");
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> {
                String selectedSeats = seatSelectionController.getSelectedSeats();
                if (!selectedSeats.equals("No seats selected")) {
                    generateSeatedTickets(selectedSeats);
                }
            });
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void generateStandingTickets() {
        int amount = Integer.parseInt(amountField.getText());
        for (int i = 0; i < amount; i++) {
            String ticketName = nameField.getText() + "_" + (i + 1);
            String ticketEmail = emailField.getText();
            String ticketPrice = priceField.getText();
            String serialNumber = generateSerialNumber();

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

                ticketNController.setNewTicket(ticketName, ticketEmail, ticketPrice, serialNumber, eventTime, eventLocation, eventName);
                ticketNController.seatLabel.setVisible(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ((Stage) nameField.getScene().getWindow()).close();
    }

    private void generateSeatedTickets(String selectedSeats) {
        String[] seatsArray = selectedSeats.split(", ");
        int amount = Math.min(seatsArray.length, Integer.parseInt(amountField.getText()));

        for (int i = 0; i < amount; i++) {
            String ticketName = nameField.getText() + "_" + (i + 1);
            String ticketEmail = emailField.getText();
            String ticketPrice = priceField.getText();
            String serialNumber = generateSerialNumber();

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

                ticketNController.setNewTicket(ticketName, ticketEmail, ticketPrice, serialNumber, eventTime, eventLocation, eventName);
                ticketNController.setSeat(seatsArray[i]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ((Stage) nameField.getScene().getWindow()).close();
    }

}
