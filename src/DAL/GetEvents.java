package DAL;

import BE.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetEvents {
    private final ConnectionManager cm = new ConnectionManager();
    private final ArrayList<Event> eventList = new ArrayList<>();

    public ArrayList<Event> getEventList(){
        try( Connection con = cm.getConnection() )
        {
            String sql = "SELECT * FROM Events";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id              = rs.getInt("EventsID");
                String name         = rs.getString("EventName");
                String time         = rs.getString("Time");
                String location     = rs.getString("Location");
                String notes         = rs.getString("Notes");
                String endDate = rs.getString("EndDate");
                String locationGuidance = rs.getString("LocationGuidance");

                Event event = new Event(id, name,time,location,notes,endDate,locationGuidance);
                eventList.add(event);
            }
            return eventList;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
