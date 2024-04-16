package DAL;

import BE.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteUserEvent {
    private final ConnectionManager cm = new ConnectionManager();

    public boolean deleteUserEventOnEventID(Event event) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM EventUser WHERE EventID = ?";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setInt(1, event.getId());
            pt.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteUserOnEventAndUser(int eventID, int userID) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM EventUser WHERE EventID = ? AND UserID = ?";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setInt(1, eventID);
            pt.setInt(2,userID);
            pt.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
