package datasource;

// https://devtut.github.io/postgresql/connect-to-postgresql-from-java.html#connecting-with-java-sql-drivermanager-and-properties

public class DataBaseConnection {
    private static java.sql.Connection connect(String url, String user, String password)
            throws ClassNotFoundException, java.sql.SQLException {
        Class.forName("org.postgresql.Driver");
        java.util.Properties props = new java.util.Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        /* don't use server prepared statements */
        props.setProperty("prepareThreshold", "0");
        /*
         * Tell the driver manager to connect to the database specified with the URL.
         * This may throw an SQLException.
         */
        return java.sql.DriverManager.getConnection(url, props);
    }
}
