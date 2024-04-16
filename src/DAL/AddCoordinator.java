package DAL;

import BE.EventCoordinator;
import BE.User;

import java.sql.*;

public class AddCoordinator {
    private final ConnectionManager cm = new ConnectionManager();

    public EventCoordinator addCoordinator(String login, String password){
        try(Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO EventCoordinators(Login,Password) VALUES (?,?)";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setString(1, login);
            pt.setString(2,password);
            int affectedRows = pt.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = pt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new EventCoordinator(generatedKeys.getInt(1),login,password);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return null;
    }
}
