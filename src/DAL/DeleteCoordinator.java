package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteCoordinator {
    private final ConnectionManager cm = new ConnectionManager();

    public boolean deleteCoordinator(int userID) {
        try(Connection con = cm.getConnection())
        {
            String sql = "DELETE FROM EventCoordinators WHERE ECID = ?";
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
