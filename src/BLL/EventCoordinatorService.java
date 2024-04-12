package BLL;

import BE.EventCoordinator;

import java.util.ArrayList;

public class EventCoordinatorService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    public ArrayList<EventCoordinator> getCoordinators(){
        return single.getCoordinators();
    }
}
