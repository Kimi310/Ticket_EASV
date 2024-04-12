package DAL;

import BE.Admin;
import BE.EventCoordinator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetEventCoordinators {
    private final ConnectionManager cm = new ConnectionManager();
    private final ArrayList<EventCoordinator> coordinatorList = new ArrayList<>();

    public ArrayList<EventCoordinator> getCoordinators(){
        try( Connection con = cm.getConnection() )
        {
            String sql = "SELECT * FROM EventCoordinators";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id              = rs.getInt("ECID");
                String login         = rs.getString("Login");
                String password         = rs.getString("Password");

                EventCoordinator ec = new EventCoordinator(id,login,password);
                coordinatorList.add(ec);
            }
            return coordinatorList;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
