package DAL;

import BE.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteECEvent {
    private final ConnectionManager cm = new ConnectionManager();

    public boolean deleteECEvent(Event event) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM EventCoordinatorEvent WHERE EventID = ?";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setInt(1, event.getId());
            pt.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteECEventOnECID(int ECID) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM EventCoordinatorEvent WHERE ECID = ?";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setInt(1, ECID);
            pt.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
