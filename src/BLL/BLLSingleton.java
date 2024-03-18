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

    public void addEvent(Event event){
        events.add(event);
        // add event to db to be done
    }

    public void deleteEvent(Event event){
        events.remove(event);
        //remove event from db to be done
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
