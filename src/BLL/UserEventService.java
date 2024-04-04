package BLL;

import BE.Event;
import BE.User;
import BE.UserEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserEventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private ArrayList<UserEvent> userEvents = single.getUserEvents();
    private ArrayList<User> users = single.getUsers();

    public ArrayList<User> getUsersForEvent(Event event){
        ArrayList<User> placeholder = new ArrayList<>();
        for (UserEvent ue: userEvents) {
            System.out.println(ue);
            if (ue.getEventID()==event.getId()){
                for (User u:users) {
                    if (u.getId()==ue.getUserID()){
                        placeholder.add(u);
                        break;
                    }
                }
            }
        }
        return placeholder;
    }
}
