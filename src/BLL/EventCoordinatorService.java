package BLL;

import BE.EventCoordinator;
import DAL.AddCoordinator;

import java.util.ArrayList;

public class EventCoordinatorService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private AddCoordinator addCoordinator = new AddCoordinator();
    public ArrayList<EventCoordinator> getCoordinators(){
        return single.getCoordinators();
    }

    public boolean addCoordinator(String login,String password){
        EventCoordinator eventCoordinator = addCoordinator.addCoordinator(login,password);
        single.addCoordinator(eventCoordinator);
        if (eventCoordinator!=null){
            return true;
        }else {
            return false;
        }
    }
}
