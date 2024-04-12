package GUI.Controller;

import BE.Event;
import BE.Ticket;
import BE.User;
import BLL.EventService;
import BLL.UserEventService;
import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class GenerateTicketController {

    public TextField nameField, amountField, emailField, priceField;
    public ChoiceBox ticketTypeChoiceBox;
    private EventCoordinatorViewController eventCoordinatorViewController;
    private Stage stage;
    private String eventTime, eventLocation, eventName;
    private UserEventService userEventService = new UserEventService();
    private EventService eventService = new EventService();
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();



    public void setEventCoordinatorController(EventCoordinatorViewController eventCoordinatorViewController) {
        this.eventCoordinatorViewController = eventCoordinatorViewController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void generateTicket(ActionEvent actionEvent) throws IOException, WriterException {
        if (!isValidNumber(priceField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ticket price must be a number");
            alert.showAndWait();
            return;
        }

        String selectedTicketType = (String) ticketTypeChoiceBox.getValue();
        if (selectedTicketType == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a ticket type");
            alert.showAndWait();
            return;
        }

        if (selectedTicketType.equals("VIP Ticket")) {
            generateVIPTickets();
        } else {
            generateNormalTickets();
        }
    }

    private String generateSerialNumber() {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public void setEventProperties(String time, String location, String name) {
        this.eventTime = time;
        this.eventLocation = location;
        this.eventName = name;
    }

    private boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void generateVIPTickets() throws IOException, WriterException {
        String ticketName = nameField.getText();
        String ticketEmail = emailField.getText();
        String ticketPrice = priceField.getText();
        String serialNumber = generateSerialNumber();

        Ticket ticket = new Ticket(ticketName, ticketEmail, ticketPrice, serialNumber, eventTime, eventLocation, eventName);
        users.addAll(userEventService.getAllUsers());
        int userId = -1;
        for (User u: users) {
            if (Objects.equals(u.getEmail(), ticketEmail)){
                userId=u.getId();
                break;
            }
        }
        if (userId==-1){
            userId=userEventService.addUser(nameField.getText(),emailField.getText());
        }
        events.addAll(eventService.getEvents());
        int eventId = -1;
        for (Event e:events) {
            if (Objects.equals(e.getName(), eventName)){
                eventId=e.getId();
            }
        }
        userEventService.addUserEvent(userId,eventId);
        openTicketPrintView(ticket, "VIP TIcket");
        ((Stage) nameField.getScene().getWindow()).close();
    }

    private void generateNormalTickets() throws IOException, WriterException {
            String ticketName = nameField.getText();
            String ticketEmail = emailField.getText();
            String ticketPrice = priceField.getText();
            String serialNumber = generateSerialNumber();

            Ticket ticket = new Ticket(ticketName, ticketEmail, ticketPrice, serialNumber, eventTime, eventLocation, eventName);
        users.addAll(userEventService.getAllUsers());
        int userId = -1;
        for (User u: users) {
            if (Objects.equals(u.getEmail(), ticketEmail)){
                userId=u.getId();
                break;
            }
        }
        if (userId==-1){
            userId=userEventService.addUser(nameField.getText(),emailField.getText());
        }

        events.addAll(eventService.getEvents());
        int eventId = -1;
        for (Event e:events) {
            if (Objects.equals(e.getName(), eventName)){
                eventId=e.getId();
            }
        }
        userEventService.addUserEvent(userId,eventId);


        openTicketPrintView(ticket, "Normal Ticket");
        ((Stage) nameField.getScene().getWindow()).close();
    }

    private void openTicketPrintView(Ticket ticket, String ticketType) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/TicketPrintView.fxml"));
        try {
            Parent root = loader.load();
            TicketPrintViewController ticketPrintController = loader.getController();
            ticketPrintController.setTicket(ticket, ticketType);

            Stage stage = new Stage();
            stage.setTitle("Print Ticket:");
            stage.setScene(new Scene(root));
            ticketPrintController.setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }



}
