package BLL;

import BE.Event;
import BE.User;
import DAL.GetEvents;
import DAL.GetUsers;

import java.util.ArrayList;

public class BLLSingleton {
    // Single instance of GUISingleton
    private static final BLLSingleton instance = new BLLSingleton();
    //Global states
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    //Getters
    private final GetEvents getEvents = new GetEvents();
    private final GetUsers getUsers = new GetUsers();
    public static BLLSingleton getInstance() {
        return instance;
    }

    public BLLSingleton(){
       events = getEvents.getEventList();
       users = getUsers.getUsersList();
    }

    // EVENTS
    public void addEventSingle(Event event){
        events.add(event);
    }

    public void deleteEventSingle(Event event){
        events.remove(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    // USERS
    public ArrayList<User> getUsers() {return users;}

}
