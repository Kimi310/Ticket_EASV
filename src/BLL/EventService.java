package BLL;

import BE.Event;
import DAL.AddEvent;
import DAL.DeleteEvent;

import java.util.ArrayList;

public class EventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private final DeleteEvent deleteEvent = new DeleteEvent();
    private final AddEvent addEvent = new AddEvent();

    public void addEvent(Event event){
        single.addEventSingle(addEvent.newEvent(event));
    }

    public void deleteEvent(Event event){
        if(deleteEvent.deleteEvent(event)){
            System.out.println("test");
            single.deleteEventSingle(event);
        }
    }

    public ArrayList<Event> getEvents (){
        return single.getEvents();
    }
}
