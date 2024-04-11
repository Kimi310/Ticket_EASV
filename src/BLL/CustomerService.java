package BLL;

import BE.Customer;

import java.util.ArrayList;

public class CustomerService {
    private final BLLSingleton single = BLLSingleton.getInstance();

    public ArrayList<Customer> getCustomer(){
        return single.getCustomer();
    }
}
