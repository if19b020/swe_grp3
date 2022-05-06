package datasource;

// https://devtut.github.io/postgresql/connect-to-postgresql-from-java.html#connecting-with-java-sql-drivermanager-and-properties

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class DataBaseConnection implements IDataBaseConnection {
    private final String connectionString;

    public DataBaseConnection(String connectionString) {
        this.connectionString = connectionString;
    }

    private java.sql.Connection connect(String url, String user, String password)
            throws ClassNotFoundException, java.sql.SQLException {
        Class.forName("org.postgresql.Driver");
        java.util.Properties props = new java.util.Properties();
        // TODO save username + password in config file
        props.setProperty("user", user);
        props.setProperty("password", password);
        /* don't use server prepared statements */
        props.setProperty("prepareThreshold", "0");
        /*
         * Tell the driver manager to connect to the database specified with the URL.
         * This may throw an SQLException.
         */

        /*try (Connection conn = DriverManager.getConnection(url, props)) {
            System.out.println(conn.getMetaData().getDatabaseProductVersion());
        } catch(SQLException e) {
            System.out.println("Error connecting to database " + Arrays.toString(e.getStackTrace()));
        }*/

        try {
            return DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Establishing database connection failed.");

    }
}
