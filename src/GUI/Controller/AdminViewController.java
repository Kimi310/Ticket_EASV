package GUI.Controller;

import BE.Event;
import BLL.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    @FXML
    private TableColumn<Event,String> nameColumn;
    @FXML
    private TableColumn<Event,String> timeColumn;
    @FXML
    private TableColumn<Event,String> locationColumn;
    @FXML
    private TableColumn<Event,String> notesColumn;
    @FXML
    private TableColumn<Event,String> endDateColumn;
    @FXML
    private TableColumn<Event,String> locationGuidanceColumn;
    @FXML
    private TableView<Event> eventTable;
    private ObservableList<Event> events= FXCollections.observableArrayList();
    private final EventService eventService = new EventService();

    private void eventTableProperties(){
        eventTable.setEditable(true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        locationGuidanceColumn.setCellValueFactory(new PropertyValueFactory<>("locationGuidance"));
        eventTable.setItems(events);
        eventTable.setRowFactory(tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount()==2 && !row.isEmpty()){
                    try {
                        viewECForEvent(row.getItem());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
    }

    private void poluteEvents(){
        ArrayList<Event> placeholder = eventService.getEvents();
        if (!placeholder.isEmpty()){
            events.addAll(placeholder);
        }
    }

    @FXML
    private void addEventCoordinator(ActionEvent actionEvent) {
    }
    @FXML
    private void deleteEvent(ActionEvent actionEvent) {
        if (eventTable.getSelectionModel().getSelectedItem()!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete the selected event?");
            alert.setContentText("This action cannot be undone.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    eventService.deleteEvent(eventTable.getSelectionModel().getSelectedItem());
                    events.clear();
                    events.addAll(eventService.getEvents());
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Event Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an event to delete.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        poluteEvents();
        eventTableProperties();
    }
    private void viewECForEvent(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/ECEventView.fxml"));
        Parent root = loader.load();
        ECEventViewController controller = loader.getController();
        controller.setEvent(event);
        controller.poluteUsers();
        controller.initializeTableView();
        Stage usersForEventStage = (Stage) eventTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }

    public void seeAllUsers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AllUsersForAdminView.fxml"));
        Parent root = loader.load();
        Stage usersForEventStage = (Stage) eventTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }

    public void seeAllCoordinators(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AllCoordinators.fxml"));
        Parent root = loader.load();
        Stage usersForEventStage = (Stage) eventTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }
}
