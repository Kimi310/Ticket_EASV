package BLL;

import BE.Admin;

import java.util.ArrayList;

public class AdminService {
    private final BLLSingleton single = BLLSingleton.getInstance();
    public ArrayList<Admin> getAdmins(){
        return single.getAdmins();
    }
}
