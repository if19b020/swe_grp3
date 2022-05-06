package datasource;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDataBaseConnection {
    int insertTour(String sqlQuery, ArrayList<Object> parameters) throws SQLException;
    <T> void deleteTour(String sqlQuery, Integer id) throws SQLException;
}
