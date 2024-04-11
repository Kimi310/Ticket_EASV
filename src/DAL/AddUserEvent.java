package DAL;

import BE.Event;
import BE.User;
import BE.UserEvent;

import java.sql.*;

public class AddUserEvent {
    private final ConnectionManager cm = new ConnectionManager();

    public UserEvent addUserEvent(int userId,int eventId){
        try(Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO EventUser(UserID,EventID) VALUES (?,?)";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setInt(1, userId);
            pt.setInt(2,eventId);
            int affectedRows = pt.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = pt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new UserEvent(userId,eventId,generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return null;
    }
}
