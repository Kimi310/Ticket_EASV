package DAL;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class ConnectionManager {    

    private final SQLServerDataSource ds;


    public ConnectionManager() {
        ds = new SQLServerDataSource();

        ds.setDatabaseName("ticketOceans");
        ds.setUser("CSe2023b_e_6");
        ds.setPassword("CSe2023bE6#23");
        ds.setServerName("10.176.111.34");

        ds.setTrustServerCertificate(true);

    }
    public Connection getConnection() throws SQLServerException{
        return ds.getConnection();
    }
}
