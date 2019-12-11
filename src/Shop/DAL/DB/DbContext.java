package Shop.DAL.DB;

import java.sql.*;

public class DbContext
{
    private Connection connection;

    public DbContext(String connectionString, String user, String password) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, user, password);
    }

    public Connection getConnection()
    {
        return connection;
    }
}
