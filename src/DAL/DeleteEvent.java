package DAL;

import BE.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteEvent {
    private final ConnectionManager cm = new ConnectionManager();

    public boolean deleteEvent(Event event) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM Events WHERE id = ?";
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setInt(1, event.getId());
            pt.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
