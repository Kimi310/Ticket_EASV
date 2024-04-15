package DAL;

import BE.EventCoordinatorEvent;
import BE.UserEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetEventCoordinatorsEvent {
    private final ConnectionManager cm = new ConnectionManager();
    private ArrayList<EventCoordinatorEvent> usersList = new ArrayList<>();

    public ArrayList<EventCoordinatorEvent> getUserEvent(){
        try( Connection con = cm.getConnection() )
        {
            String sql = "SELECT * FROM EventCoordinatorEvent";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id              = rs.getInt("ECEID");
                int userID         = rs.getInt("ECID");
                int eventID         = rs.getInt("EventID");

                EventCoordinatorEvent userEvent = new EventCoordinatorEvent(id,userID,eventID);
                usersList.add(userEvent);
            }
            return usersList;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
