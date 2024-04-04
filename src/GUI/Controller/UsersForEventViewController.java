package GUI.Controller;

import BE.Event;
import BE.User;
import BLL.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UsersForEventViewController implements Initializable {
    public TableView<User> usersTable;
    public TableColumn<User,String> userNameColumn;
    public TableColumn<User,String> emailColumn;
    private ObservableList<User> users = FXCollections.observableArrayList();
    private final UserService userService = new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        poluteUsers();
        initializeTableView();
    }

    private void initializeTableView(){
        usersTable.setEditable(true);
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        usersTable.setItems(users);
        usersTable.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount()==2 && !row.isEmpty()){
                    User rowUser = row.getItem();
                    //implement preview of printing a ticket
                }
            });
            return row;
        });
    }

    private void poluteUsers(){
        ArrayList<User> placeholder = userService.getUsers();
        if (!placeholder.isEmpty()){
            users.addAll(placeholder);
        }
    }

    public void editUser(ActionEvent actionEvent) {
    }

    public void addUser(ActionEvent actionEvent) {
    }

    public void deleteUser(ActionEvent actionEvent) {
    }


}
