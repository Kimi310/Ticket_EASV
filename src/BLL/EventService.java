package BLL;

import BE.Event;

import java.util.ArrayList;

public class EventService {
    private final BLLSingleton single = BLLSingleton.getInstance();

    public void addEvent(Event event){
        single.addEventSingle(event);
        //db adding to be done
    }

    public void deleteEvent(Event event){
        single.deleteEventSingle(event);
        //db delete event
    }

    public ArrayList<Event> getEvents (){
        return single.getEvents();
    }
}
