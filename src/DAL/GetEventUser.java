package DAL;

import BE.User;
import BE.UserEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetEventUser {
    private final ConnectionManager cm = new ConnectionManager();
    private ArrayList<UserEvent> usersList = new ArrayList<>();

    public ArrayList<UserEvent> getUserEvent(){
        try( Connection con = cm.getConnection() )
        {
            String sql = "SELECT * FROM EventUser";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id              = rs.getInt("EventUserID");
                int userID         = rs.getInt("UserID");
                int eventID         = rs.getInt("EventID");

                UserEvent userEvent = new UserEvent(id,userID,eventID);
                usersList.add(userEvent);
            }
            System.out.println(usersList);
            return usersList;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
