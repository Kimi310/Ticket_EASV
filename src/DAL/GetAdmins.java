package DAL;

import BE.Admin;
import BE.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetAdmins {
    private final ConnectionManager cm = new ConnectionManager();
    private final ArrayList<Admin> adminsList = new ArrayList<>();

    public ArrayList<Admin> getAdmins(){
        try( Connection con = cm.getConnection() )
        {
            String sql = "SELECT * FROM Admins";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id              = rs.getInt("AdminID");
                String login         = rs.getString("Login");
                String password         = rs.getString("Password");

                Admin admin = new Admin(id,login,password);
                adminsList.add(admin);
            }
            return adminsList;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
