package BLL;

public class BLLSingleton {
    // Single instance of GUISingleton
    private static final BLLSingleton instance = new BLLSingleton();
    public static BLLSingleton getInstance() {
        return instance;
    }


}
