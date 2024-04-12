package BE;

public class EventCoordinator {
    private int id;
    private String login;
    private String password;

    public EventCoordinator(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


}
