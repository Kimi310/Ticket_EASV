package BLL;

import BE.*;
import DAL.*;

import java.util.ArrayList;

public class BLLSingleton {
    // Single instance of GUISingleton
    private static final BLLSingleton instance = new BLLSingleton();
    //Global states
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<UserEvent> userEvents = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<EventCoordinator> coordinators = new ArrayList<>();
    private ArrayList<EventCoordinatorEvent> ECEvents = new ArrayList<>();
    //Getters
    private final GetEvents getEvents = new GetEvents();
    private final GetUsers getUsers = new GetUsers();
    private final GetEventUser getEventUser = new GetEventUser();
    private final GetAdmins getAdmins = new GetAdmins();
    private final GetEventCoordinatorsEvent getEventCoordinatorsEvent = new GetEventCoordinatorsEvent();
    private final GetEventCoordinators getEventCoordinators = new GetEventCoordinators();

    public static BLLSingleton getInstance() {
        return instance;
    }

    public BLLSingleton(){
       events = getEvents.getEventList();
       users = getUsers.getUsersList();
       userEvents = getEventUser.getUserEvent();
       admins = getAdmins.getAdmins();
       coordinators = getEventCoordinators.getCoordinators();
       ECEvents = getEventCoordinatorsEvent.getUserEvent();
    }

    // EVENTS
    public Event addEventSingle(Event event){
        events.add(event);
        return event;
    }

    public void deleteEventSingle(Event event){
        events.remove(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    // USERS
    public ArrayList<User> getUsers() {return users;}
    public void addUser(User user){
        users.add(user);
    }

    // USEREVENT
    public ArrayList<UserEvent> getUserEvents() {return userEvents;};
    public void addUserEvent(UserEvent userEvent){
        userEvents.add(userEvent);
    }
    public void deleteUserEvent(Event event){
        userEvents.removeIf(ue -> ue.getEventID() == event.getId());
    }

    public void deleteUserEventOnUserAndEvent(int eventID,int userID){
        userEvents.removeIf(ue -> ue.getEventID() == eventID && ue.getUserID() == userID);
    }



    //ADMINS
    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    //COORDINATORS
    public ArrayList<EventCoordinator> getCoordinators() {
        return coordinators;
    }
    //ECEvent
    public ArrayList<EventCoordinatorEvent> getECEvents() {return ECEvents;};
    public void addECEvent(EventCoordinatorEvent userEvent){
        ECEvents.add(userEvent);
    }
    public void deleteECEvent(Event event){
        ECEvents.removeIf(ue -> ue.getEventID() == event.getId());
    }
    public void addCoordinator(EventCoordinator coordinator){
        coordinators.add(coordinator);
    }


}
