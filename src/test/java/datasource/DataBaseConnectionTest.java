package datasource;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class DataBaseConnectionTest {
    private String user = "postgres";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String password = "password";

    @Test
    public void connectTest() throws SQLException, ClassNotFoundException {
        DataBaseConnection dbc = new DataBaseConnection();
        java.sql.Connection connection = dbc.connect(url, user, password);
        assertEquals(1,1);
    }

}