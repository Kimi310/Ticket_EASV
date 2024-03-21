package BLL;

import BE.Event;
import DAL.GetEvents;

import java.util.ArrayList;

public class BLLSingleton {
    // Single instance of GUISingleton
    private static final BLLSingleton instance = new BLLSingleton();
    //Global states
    private ArrayList<Event> events = new ArrayList<>();
    //Getters
    private final GetEvents getEvents = new GetEvents();
    public static BLLSingleton getInstance() {
        return instance;
    }

//    public BLLSingleton(){
//        events = getEvents.getEventList();
//    }

    public void addEventSingle(Event event){
        events.add(event);
    }

    public void deleteEventSingle(Event event){
        events.remove(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
