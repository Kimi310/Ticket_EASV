package DAL;

import BE.EventCoordinatorEvent;
import BE.UserEvent;

import java.sql.*;

public class AddECToEvent {
    private final ConnectionManager cm = new ConnectionManager();

    public EventCoordinatorEvent addECToEvent(int ECID, int eventId){
        try(Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO EventCoordinatorEvent(ECID,EventID) VALUES (?,?)";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setInt(1, ECID);
            pt.setInt(2,eventId);
            int affectedRows = pt.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = pt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new EventCoordinatorEvent(generatedKeys.getInt(1),ECID,eventId );
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return null;
    }
}
