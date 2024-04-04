package BLL;

import BE.User;

import java.util.ArrayList;

public class UserService {
    private final BLLSingleton single = BLLSingleton.getInstance();

    public ArrayList<User> getUsers(){
        return single.getUsers();
    }
}
