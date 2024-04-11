package BLL;

import BE.Event;
import BE.Customer;
import BE.CustomerEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerEventService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    private ArrayList<CustomerEvent> customerEvents = single.getCustomerEvents();
    private ArrayList<Customer> customer = single.getCustomer();

    public ArrayList<Customer> getCustomerForEvent(Event event){
        ArrayList<Customer> placeholder = new ArrayList<>();
        for (CustomerEvent ue: customerEvents) {
            if (ue.getEventID()==event.getId()){
                for (Customer u:customer) {
                    if (u.getId()==ue.getEventID()){
                        placeholder.add(u);
                        break;
                    }
                }
            }
        }
        return placeholder;
    }
}