package GUI.Controller;

import BE.Event;
import BLL.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private EventService eventService = new EventService();

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
    }

    @FXML
    private void deleteEvent(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        poluteEvents();
        eventTableProperties();
    }
}
