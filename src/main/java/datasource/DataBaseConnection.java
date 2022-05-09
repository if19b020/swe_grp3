package datasource;

// https://devtut.github.io/postgresql/connect-to-postgresql-from-java.html#connecting-with-java-sql-drivermanager-and-properties

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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

        try {
            return DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Database connection failed.");

    }

    @Override
    public int insertTour(String sqlQuery, ArrayList<Object> parameters) throws SQLException {
        // TODO implement
        return 0;
    }

    @Override
    public <T> void deleteTour(String sqlQuery, Integer id) throws SQLException {
        // TODO implement
    }
}
