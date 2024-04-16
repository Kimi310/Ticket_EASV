package DAL;

import BE.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteUser {
    private final ConnectionManager cm = new ConnectionManager();

    public boolean deleteUser(int userID) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM Users WHERE UserID = ?";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setInt(1, userID);
            pt.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
