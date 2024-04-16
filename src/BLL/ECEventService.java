package BLL;

import BE.*;
import DAL.AddECToEvent;

import java.util.ArrayList;

public class ECEventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private ArrayList<EventCoordinatorEvent> ECEvents = new ArrayList<>();
    private ArrayList<EventCoordinator> coordinators = single.getCoordinators();
    private AddECToEvent addECToEvent = new AddECToEvent();
    public ArrayList<EventCoordinator> getECEvents(Event event){
        ECEvents.clear();
        ECEvents.addAll(single.getECEvents());
        ArrayList<EventCoordinator> placeholder = new ArrayList<>();
        for (EventCoordinatorEvent ECE: ECEvents) {
            if (ECE.getEventID()==event.getId()){
                    for (EventCoordinator ec:coordinators) {
                        if (ec.getId()==ECE.getECID()){
                            placeholder.add(ec);
                        }
                    }
                }
        }
        return placeholder;
    }

    public void addECToEvent(EventCoordinator ec, Event event){
        System.out.println("Gets to service");
        single.addECEvent(addECToEvent.addECToEvent(ec.getId(),event.getId()));
    }
}
