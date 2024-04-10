package DAL;

import BE.Event;
import BE.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetUsers {
    private final ConnectionManager cm = new ConnectionManager();
    private final ArrayList<User> usersList = new ArrayList<>();

    public ArrayList<User> getUsersList(){
        try( Connection con = cm.getConnection() )
        {
            String sql = "SELECT * FROM Users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id              = rs.getInt("UserID");
                String name         = rs.getString("UserName");
                String email         = rs.getString("Email");

                User user = new User(id,name,email);
                usersList.add(user);
            }
            return usersList;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
