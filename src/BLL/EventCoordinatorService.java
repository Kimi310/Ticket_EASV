package BLL;

import BE.EventCoordinator;
import DAL.AddCoordinator;
import DAL.DeleteCoordinator;
import DAL.DeleteECEvent;
import DAL.DeleteUserEvent;

import java.util.ArrayList;

public class EventCoordinatorService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private AddCoordinator addCoordinator = new AddCoordinator();
    public ArrayList<EventCoordinator> getCoordinators(){
        return single.getCoordinators();
    }
    private DeleteCoordinator deleteCoordinator = new DeleteCoordinator();
    private DeleteECEvent deleteECEvent = new DeleteECEvent();

    public boolean addCoordinator(String login,String password){
        EventCoordinator eventCoordinator = addCoordinator.addCoordinator(login,password);
        single.addCoordinator(eventCoordinator);
        if (eventCoordinator!=null){
            return true;
        }else {
            return false;
        }
    }

    public void deleteEventCoordinator(int ECID){
        if (deleteECEvent.deleteECEventOnECID(ECID)){
            single.deleteECEventOnECID(ECID);
            if (deleteCoordinator.deleteCoordinator(ECID)){
                single.deleteCoordinator(ECID);
            }
        }
    }
}
