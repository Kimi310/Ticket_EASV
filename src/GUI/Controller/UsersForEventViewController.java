package GUI.Controller;

import BE.Event;
import BE.EventCoordinator;
import BE.User;
import BLL.UserEventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsersForEventViewController{
    public TableView<User> usersTable;
    public TableColumn<User,String> userNameColumn;
    public TableColumn<User,String> emailColumn;
    private ObservableList<User> users = FXCollections.observableArrayList();
    private final UserEventService userEventService = new UserEventService();
    private Event event;
    private EventCoordinator coordinator;

    public void initializeTableView(){
        usersTable.setEditable(true);
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        usersTable.setItems(users);
    }

    public void poluteUsers(){
        users.clear();
        ArrayList<User> placeholder = userEventService.getUsersForEvent(event);
        if (!placeholder.isEmpty()){
            users.addAll(placeholder);
        }
    }

    public void setEvent(Event e){
        event = e;
    }

    public void deleteUser(ActionEvent actionEvent) {
        if (usersTable.getSelectionModel().getSelectedItem()!=null){
            userEventService.deleteUserFromEvent(event.getId(),usersTable.getSelectionModel().getSelectedItem().getId());
            poluteUsers();
        }
    }

    public void setCoordinator(EventCoordinator ec){
        coordinator = ec;
    }


    public void goBackBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EventCoordinatorView.fxml"));
        Parent root = loader.load();
        EventCoordinatorViewController controller = loader.getController();
        controller.setEventCoordinator(coordinator);
        controller.poluteEvents();
        controller.eventTableProperties();
        Stage usersForEventStage = (Stage) usersTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }
    @FXML
    private void goToEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EventCoordinatorView.fxml"));
        Parent root = loader.load();
        EventCoordinatorViewController controller = loader.getController();
        controller.setEventCoordinator(coordinator);
        controller.poluteEvents();
        controller.eventTableProperties();
        Stage usersForEventStage = (Stage) usersTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }
    @FXML
    private void goToAllUsers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AllUsersView.fxml"));
        Parent root = loader.load();
        AllUsersViewController controller = loader.getController();
        controller.setEventCoordinator(coordinator);
        Stage usersForEventStage = (Stage) usersTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }
}
