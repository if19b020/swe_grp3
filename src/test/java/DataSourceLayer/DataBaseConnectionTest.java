package DataSourceLayer;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;


class DataBaseConnectionTest {
    private String user = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String password = "password";

    @Test
    public void connectTest() throws SQLException, ClassNotFoundException {
        //DataBaseConnection dbc = new DataBaseConnection();
        //java.sql.Connection connection = dbc.connect(url, user, password);
        //assertNotNull(connection);
    }

}