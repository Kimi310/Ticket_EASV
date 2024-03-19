package DAL;

import BE.Event;

import java.sql.*;

public class AddEvent {
    private final ConnectionManager cm = new ConnectionManager();

    public Event newEvent(Event event){
        try(Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO Events(EventName,Time,Location,Notes,EndDate,LocationGuidance) VALUES (?,?,?,?,?,?)";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setString(1, event.getName());
            pt.setString(2,event.getTime());
            pt.setString(3,event.getLocation());
            pt.setString(4,event.getNotes());
            pt.setString(5,event.getEndDate());
            pt.setString(6,event.getLocationGuidance());
            int affectedRows = pt.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = pt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Event(generatedKeys.getInt(1), event.getName(), event.getTime(), event.getLocation(), event.getNotes(), event.getEndDate(),event.getLocationGuidance());
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return null;
    }
}
