package DAL;

import BE.User;
import BE.UserEvent;

import java.sql.*;

public class AddUser {
    private final ConnectionManager cm = new ConnectionManager();

    public User addUser(String userName, String email){
        try(Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO Users(UserName,Email) VALUES (?,?)";
            PreparedStatement pt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pt.setString(1, userName);
            pt.setString(2,email);
            int affectedRows = pt.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            try (ResultSet generatedKeys = pt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new User(generatedKeys.getInt(1),userName,email);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return null;
    }
}
