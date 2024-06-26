package GUI.Controller;

import BE.Event;
import BE.EventCoordinator;
import BLL.ECEventService;
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

public class EventCoordinatorViewController{
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
    private final ECEventService ecEventService = new ECEventService();
    private EventCoordinator coordinator;

    public void eventTableProperties(){
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
                    Event rowEvent = row.getItem();
                    try {
                        viewUsersForEvent(rowEvent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
    }

    public void poluteEvents(){
        ArrayList<Event> placeholder = eventService.getEventsForEC(coordinator);
        if (!placeholder.isEmpty()){
            events.addAll(placeholder);
        }
    }
    @FXML
    private void addEvent(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateEventWindow.fxml"));
        Parent root;
        try {
            root = loader.load();
            CreateEventViewController addEventController = loader.getController();
            addEventController.setEventCoordinatorController(this);
            Stage stage = new Stage();
            stage.setTitle("Add Event");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEventCoordinator(EventCoordinator ec){
        coordinator = ec;
    }

    public void editEvent(ActionEvent actionEvent) {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateEventWindow.fxml"));
            Parent root;
            try {
                root = loader.load();
                CreateEventViewController createEventController = loader.getController();
                createEventController.setEventCoordinatorController(this);

                createEventController.eventNameTF.setText(selectedEvent.getName());
                //dates are messed up, have to fix this so it retreives the dates as well, rn it only retrieves time
                //createEventController.startingTimeTF.setText(selectedEvent.getTime());
                createEventController.eventLocationTF.setText(selectedEvent.getLocation());
                createEventController.otherInfoTF.setText(selectedEvent.getNotes());
                createEventController.howToArriveTF.setText(selectedEvent.getLocationGuidance());

                Stage stage = new Stage();
                stage.setTitle("Edit event");
                stage.setScene(new Scene(root));
                createEventController.setStage(stage);
                stage.show();
                createEventController.confirmCreateEventButton.setText("Save Changes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Event Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Event to edit.");
            alert.showAndWait();
        }
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

    protected void updateEventProperties(String name, String time, String location, String notes, String endDate, String locationGuidance){
        //Check If the event already exist
        boolean eventExists = false;
        Event existingEvent = null;

        //modify existing event properties
        for (Event event : eventTable.getItems()){
            if (event.getName().equals(name)){
                existingEvent = event;
                existingEvent.setName(name);
                existingEvent.setTime(time);
                existingEvent.setLocation(location);
                existingEvent.setNotes(notes);
                existingEvent.setEndDate(endDate);
                existingEvent.setLocationGuidance(locationGuidance);
                //implement db update of event

                eventExists = true;
                break;
            }
        }

        //If the event doesnt exist, add a new one
        if (!eventExists){
            Event newEvent = eventService.addEvent(new Event(name, time, location, notes, endDate, locationGuidance));
            ecEventService.addECToEvent(coordinator,newEvent);
            events.clear();
            events.addAll(eventService.getEventsForEC(coordinator));
        }
    }


    private void viewUsersForEvent(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/UsersForEventView.fxml"));
        Parent root = loader.load();
        UsersForEventViewController controller = loader.getController();
        controller.setEvent(event);
        controller.setCoordinator(coordinator);
        controller.poluteUsers();
        controller.initializeTableView();
        Stage usersForEventStage = (Stage) eventTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }

    public void openTicketGenerator(ActionEvent actionEvent) {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/GenerateTicketView.fxml"));
            Parent root;
            try {
                root = loader.load();
                GenerateTicketController ticketController = loader.getController();
                ticketController.setEventCoordinatorController(this);
                ticketController.setEventProperties(selectedEvent.getTime(), selectedEvent.getLocation(), selectedEvent.getName());
                Stage stage = new Stage();
                stage.setTitle("Generate Ticket(s)");
                stage.setScene(new Scene(root));
                ticketController.setStage(stage);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Event Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Event to generate a Ticket(s).");
            alert.showAndWait();
        }
    }
    @FXML
    private void goToAllUsers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AllUsersView.fxml"));
        Parent root = loader.load();
        AllUsersViewController controller = loader.getController();
        controller.setEventCoordinator(coordinator);
        Stage usersForEventStage = (Stage) eventTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }
}
