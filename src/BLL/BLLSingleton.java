package BLL;

import BE.Event;

import java.util.ArrayList;

public class BLLSingleton {
    // Single instance of GUISingleton
    private static final BLLSingleton instance = new BLLSingleton();
    //Global states
    private ArrayList<Event> events;
    public static BLLSingleton getInstance() {
        return instance;
    }

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
