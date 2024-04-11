package BLL;

import BE.Event;
import BE.User;
import BE.UserEvent;
import DAL.AddUser;
import DAL.AddUserEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserEventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private ArrayList<UserEvent> userEvents = single.getUserEvents();
    private ArrayList<User> users = single.getUsers();
    private AddUser addUser = new AddUser();
    private AddUserEvent addUserEvent = new AddUserEvent();

    public ArrayList<User> getUsersForEvent(Event event){
        ArrayList<User> placeholder = new ArrayList<>();
        for (UserEvent ue: userEvents) {
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

    public void addUser(String userName, String email){
        single.addUser(addUser.addUser(userName,email));
    }

    public void addUserEvent(int userId,int eventId){
        single.addUserEvent(addUserEvent.addUserEvent(userId,eventId));
    }
}
