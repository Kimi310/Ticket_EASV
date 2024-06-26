package BLL;

import BE.Event;
import BE.User;
import BE.UserEvent;
import DAL.AddUser;
import DAL.AddUserEvent;
import DAL.DeleteUser;
import DAL.DeleteUserEvent;
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
    private final DeleteUserEvent deleteUserEvent = new DeleteUserEvent();
    private DeleteUser deleteUser = new DeleteUser();

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
    public ArrayList<User> getAllUsers(){
        return users;
    }
    public int addUser(String userName, String email){
        User user = addUser.addUser(userName,email);
        single.addUser(user);
        return user.getId();
    }

    public void addUserEvent(int userId,int eventId){
        single.addUserEvent(addUserEvent.addUserEvent(userId,eventId));
    }

    public boolean deleteUserFromEvent(int eventID, int userID){
        if (deleteUserEvent.deleteUserOnEventAndUser(eventID,userID)){
            single.deleteUserEventOnUserAndEvent(eventID,userID);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int userID){
        if (deleteUserEvent.deleteUserEventOnUser(userID)){
            single.deleteUserEventOnUser(userID);
            if (deleteUser.deleteUser(userID)){
                single.deleteUser(userID);
                return true;
            }
        }
        return false;
    }
}
