package GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SeatSelectionController implements Initializable {

    @FXML
    private GridPane gridPane;

    private int rows = 5;
    private int columns = 5;
    private Button[][] seats;
    private int selectedSeatCount = 0;
    private int ticketAmount;
    @FXML
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSeats();
    }

    private void initializeSeats() {
        seats = new Button[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Button seat = new Button("Seat " + (i * columns + j + 1));
                seat.setUserData(false); // Set seat as unselected initially
                seat.setOnAction(event -> toggleSeatSelection(seat));
                seats[i][j] = seat;
                gridPane.add(seat, j, i);
            }
        }
    }

    private void toggleSeatSelection(Button seat) {
        boolean isSelected = (boolean) seat.getUserData();
        if (isSelected) {
            selectedSeatCount--;
        } else {
            selectedSeatCount++;
        }

        if (selectedSeatCount > ticketAmount) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Number of selected seats cannot exceed ticket amount.");
            alert.showAndWait();
            return;
        }

        seat.setUserData(!isSelected);
        seat.setStyle(isSelected ? "-fx-background-color: white;" : "-fx-background-color: lightgreen;");
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getSelectedSeats() {
        StringBuilder selectedSeats = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Button seat = seats[i][j];
                if ((boolean) seat.getUserData()) {
                    selectedSeats.append(seat.getText()).append(", ");
                }
            }
        }
        return selectedSeats.toString().isEmpty() ? "No seats selected" : selectedSeats.toString();
    }
}