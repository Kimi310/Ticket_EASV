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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventCoordinatorViewController implements Initializable {
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

    }

    private void poluteEvents(){
        ArrayList<Event> placeholder = eventService.getEvents();
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

    public void editEvent(ActionEvent actionEvent) {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateEventWindow.fxml"));
            Parent root;
            try {
                root = loader.load();

                CreateEventViewController createEventController = loader.getController();

                createEventController.setEventCoordinatorController(this);

                // Set the fields in AddMovieWindowController with the selected movie properties
                createEventController.eventNameTF.setText(selectedEvent.getName());
                //createEventController.startingTimeTF.setText(selectedEvent.getTime());
                createEventController.eventLocationTF.setText(selectedEvent.getLocation());
                createEventController.otherInfoTF.setText(selectedEvent.getNotes());
                createEventController.howToArriveTF.setText(selectedEvent.getLocationGuidance());


                // Create a new stage for the AddMovieWindow
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
            // Show an alert or message indicating that no Movie is selected
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
            eventService.deleteEvent(eventTable.getSelectionModel().getSelectedItem());
            events.clear();
            events.addAll(eventService.getEvents());
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
            Event newEvent = new Event(name, time, location, notes, endDate, locationGuidance);
            //implement db addition of event

            eventTable.getItems().add(newEvent);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        poluteEvents();
        eventTableProperties();
    }


}
