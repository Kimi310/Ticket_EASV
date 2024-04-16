package BLL;

import BE.Event;
import BE.EventCoordinator;
import BE.EventCoordinatorEvent;
import DAL.AddEvent;
import DAL.DeleteECEvent;
import DAL.DeleteEvent;
import DAL.DeleteUserEvent;

import java.util.ArrayList;

public class EventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private final DeleteEvent deleteEvent = new DeleteEvent();
    private final AddEvent addEvent = new AddEvent();
    private final DeleteUserEvent deleteUserEvent = new DeleteUserEvent();
    private final DeleteECEvent deleteECEvent = new DeleteECEvent();

    public Event addEvent(Event event){
        return single.addEventSingle(addEvent.newEvent(event));
    }

    public void deleteEvent(Event event){
        if(deleteUserEvent.deleteUserEventOnEventID(event)){
            single.deleteUserEvent(event);
            if (deleteECEvent.deleteECEvent(event)){
                single.deleteECEvent(event);
                if(deleteEvent.deleteEvent(event)){
                    System.out.println("test");
                    single.deleteEventSingle(event);
                }
            }
        }
    }

    public ArrayList<Event> getEventsForEC(EventCoordinator ec) {
        ArrayList<Event> placeholder = new ArrayList<>();
        for (EventCoordinatorEvent e:single.getECEvents()) {
            if (e.getECID() == ec.getId()){
                for (Event event:single.getEvents()) {
                    if (e.getEventID()==event.getId()){
                        placeholder.add(event);
                    }
                }
            }
        }
        return placeholder;
    }

    public ArrayList<Event> getEvents (){
        return single.getEvents();
    }
}
