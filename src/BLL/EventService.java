package BLL;

import BE.Event;
import DAL.DeleteEvent;

import java.util.ArrayList;

public class EventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private final DeleteEvent deleteEvent = new DeleteEvent();

    public void addEvent(Event event){
        single.addEventSingle(event);
        //db adding to be done
    }

    public void deleteEvent(Event event){
        single.deleteEventSingle(event);
        deleteEvent.deleteEvent(event);
    }

    public ArrayList<Event> getEvents (){
        return single.getEvents();
    }
}
