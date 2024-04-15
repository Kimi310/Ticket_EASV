package BLL;

import BE.*;

import java.util.ArrayList;

public class ECEventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private ArrayList<EventCoordinatorEvent> ECEvents = new ArrayList<>();
    private ArrayList<EventCoordinator> coordinators = single.getCoordinators();
    public ArrayList<EventCoordinator> getECEvents(Event event){
        ECEvents.addAll(single.getECEvents());
        ArrayList<EventCoordinator> placeholder = new ArrayList<>();
        for (EventCoordinatorEvent ECE: ECEvents) {
            if (ECE.getEventID()==event.getId()){
                    for (EventCoordinator ec:coordinators) {
                        if (ec.getId()==ECE.getECID()){
                            placeholder.add(ec);
                            break;
                        }
                    }
                }
            return placeholder;
        }
        return null;
    }
}
