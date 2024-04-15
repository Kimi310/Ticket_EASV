package GUI.Controller;

import BE.Event;
import BE.EventCoordinator;
import BE.EventCoordinatorEvent;
import BE.User;
import BLL.ECEventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ECEventViewController {
    public TableView<EventCoordinator> ECTable = new TableView<>();
    public TableColumn<EventCoordinator,String> userNameColumn;
    private ECEventService ECEventService = new ECEventService();

    private ObservableList<EventCoordinator> coordinators = FXCollections.observableArrayList();

    private Event event;


    public void goToEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AdminView.fxml"));
        Parent root = loader.load();
        Stage usersForEventStage = (Stage) ECTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }

    public void addEC(ActionEvent actionEvent) {
    }

    public void deleteEC(ActionEvent actionEvent) {
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void initializeTableView(){
        ECTable.setEditable(true);
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        ECTable.setItems(coordinators);
    }

    public void poluteUsers(){
        ArrayList<EventCoordinator> placeholder = ECEventService.getECEvents(event);
        if (!placeholder.isEmpty()){
            coordinators.addAll(placeholder);
        }
    }
}
