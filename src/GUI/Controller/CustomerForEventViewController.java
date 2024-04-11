package GUI.Controller;

import BE.Event;
import BE.Customer;
import BLL.CustomerEventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class CustomerForEventViewController implements Initializable {
    public TableView<Customer> customerTable;
    public TableColumn<Customer,String> customerNameColumn;
    public TableColumn<Customer,String> emailColumn;
    public ObservableList<Customer> customer = FXCollections.observableArrayList();
    private final CustomerEventService customerEventService = new CustomerEventService();
    public Event event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initializeTableView(){
        customerTable.setEditable(true);
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        customerTable.setItems(customer);
        customerTable.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount()==2 && !row.isEmpty()){
                    Customer rowCustomer = row.getItem();
                    //implement preview of printing a ticket
                }
            });
            return row;
        });
    }

    public void poluteCustomer(){
        ArrayList<Customer> placeholder = customerEventService.getCustomerForEvent(event);
        if (!placeholder.isEmpty()){
            customer.addAll(placeholder);
        }
    }

    public void setEvent(Event e){
        event = e;
    }

    //ADD A CUSTOMER TO AN EVENT CREATED
    public void addCustomerToEventButton(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateCustomerWindow.fxml"));
        Parent root;
        try {
            root = loader.load();
            CreateNewCustomerController addCustomerController = loader.getController();
            addCustomerController.setCustomerForEventController(this);
            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addCustomerToEvent(String customerName, String customerLastName, String customerBirthDate, String customerEmail) {
        //Check If the Customer already exist
        boolean customerExists = false;

        //modify existing Customer properties
        for (Customer customer: customerTable.getItems()) {
            if (customer.getName().equals(customerName)) {
                customer.setName(customerName);
                customer.setLastName(customerLastName);
                customer.setBirthDate(customerBirthDate);
                customer.setEmail(customerEmail);
            }
        }
    }

    public void editCustomerBT(ActionEvent actionEvent) {}

    public void deleteCustomerBT(ActionEvent actionEvent) { }


}
