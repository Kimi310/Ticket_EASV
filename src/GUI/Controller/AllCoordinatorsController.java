package GUI.Controller;

import BE.Event;
import BE.EventCoordinator;
import BE.User;
import BLL.EventCoordinatorService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllCoordinatorsController implements Initializable {
    @FXML
    private TableView<EventCoordinator> allCoordinatorsTable;
    @FXML
    private TableColumn<EventCoordinator,String> allUserNameColumn;
    private ObservableList<EventCoordinator> allCoordinators = FXCollections.observableArrayList();
    private final EventCoordinatorService eventCoordinatorService = new EventCoordinatorService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        poluteAllCoordinators();
        initializeTableView();
    }
    @FXML
    private void goToEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AdminView.fxml"));
        Parent root = loader.load();
        Stage usersForEventStage = (Stage) allCoordinatorsTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }

    private void initializeTableView(){
        allCoordinatorsTable.setEditable(true);
        allUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        allCoordinatorsTable.setItems(allCoordinators);
    }

    public void poluteAllCoordinators(){
        allCoordinators.clear();
        allCoordinators.addAll(eventCoordinatorService.getCoordinators());
    }


    public void seeAllUsers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AllUsersForAdminView.fxml"));
        Parent root = loader.load();
        Stage usersForEventStage = (Stage) allCoordinatorsTable.getScene().getWindow();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }


    @FXML
    private void addCoordinator(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AddEventCoordinator.fxml"));
        Parent root = loader.load();
        AddEventCoordinatorController controller = loader.getController();
        controller.setController(this);
        Stage usersForEventStage = new Stage();
        usersForEventStage.setScene(new Scene(root));
        usersForEventStage.show();
    }

    @FXML
    private void deleteCoordinator(ActionEvent actionEvent) {
    }
}
