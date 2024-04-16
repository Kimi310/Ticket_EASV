package GUI.Controller;

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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.naming.spi.InitialContextFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllUsersViewController implements Initializable {
    @FXML
    private TableView<User> allUsersTable;
    @FXML
    private TableColumn<User,String> allEmailColumn;
    @FXML
    private TableColumn<User,String> allUserNameColumn;
    private ObservableList<User> allUsers = FXCollections.observableArrayList();
    private final UserEventService userEventService = new UserEventService();
    private EventCoordinator coordinator;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        poluteAllUsers();
        initializeTableView();
    }

    public void setEventCoordinator(EventCoordinator ec){
        coordinator = ec;
    }
    @FXML
    private void goToEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EventCoordinatorView.fxml"));
        Parent root = loader.load();
        EventCoordinatorViewController controller = loader.getController();
        controller.setEventCoordinator(coordinator);
        controller.poluteEvents();
        controller.eventTableProperties();
        Stage usersForEventStage = (Stage) allUsersTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }
    private void poluteAllUsers(){
        allUsers.clear();
        allUsers.addAll(userEventService.getAllUsers());
    }

    private void initializeTableView(){
        allUsersTable.setEditable(true);
        allUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        allUsersTable.setItems(allUsers);
    }

    @FXML
    private void deleteUser(ActionEvent actionEvent) {
        if (allUsersTable.getSelectionModel().getSelectedItem()!=null){
            userEventService.deleteUser(allUsersTable.getSelectionModel().getSelectedItem().getId());
            poluteAllUsers();
        }
    }


}
