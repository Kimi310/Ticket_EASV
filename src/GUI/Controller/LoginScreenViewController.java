package GUI.Controller;

import BE.Admin;
import BE.EventCoordinator;
import BLL.AdminService;
import BLL.EventCoordinatorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginScreenViewController implements Initializable {
    public Label errormsg;
    @FXML
    private  TextField passwordtxt;
    @FXML
    private TextField logintxt;
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private ArrayList<EventCoordinator> coordinators = new ArrayList<>();
    private final AdminService adminService = new AdminService();
    private final EventCoordinatorService eventCoordinatorService = new EventCoordinatorService();
    private String login, password;

    @FXML
    private void logIn(ActionEvent actionEvent) throws IOException {
        login = logintxt.getText();
        password = passwordtxt.getText();
        for (Admin admin:admins) {
            if (Objects.equals(admin.getLogin(), login) && Objects.equals(admin.getPassword(), password)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AdminView.fxml"));
                Parent root = loader.load();
                Stage usersForEventStage = (Stage) passwordtxt.getScene().getWindow();
                usersForEventStage.setScene(new Scene(root));
                usersForEventStage.show();
                return;
            }
        }

        for (EventCoordinator ec:coordinators) {
            if (Objects.equals(ec.getLogin(), login) && Objects.equals(ec.getPassword(), password)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EventCoordinatorView.fxml"));
                Parent root = loader.load();
                Stage usersForEventStage = (Stage) passwordtxt.getScene().getWindow();
                usersForEventStage.setScene(new Scene(root));
                usersForEventStage.show();
                return;
            }
        }
        errormsg.setVisible(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admins.addAll(adminService.getAdmins());
        coordinators.addAll(eventCoordinatorService.getCoordinators());
    }
}
