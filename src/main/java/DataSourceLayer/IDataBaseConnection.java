package DataSourceLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDataBaseConnection {
    int InsertNew(String sqlQuery, ArrayList<Object> parameters) throws SQLException;
    <T> List<T> TourReader(String sqlQuery, Class<T> tourType) throws SQLException;
    <T> List<T> TourReader(String sqlQuery, ArrayList<Object> parameters, Class<T> tourType) throws SQLException;
    <T> void delete(String sqlQuery, Integer id) throws SQLException;
    void UpdateItem(String sql_update_by_id, ArrayList<Object> parameters) throws SQLException;
    void UpdateLog(String sql_update_by_id, ArrayList<Object> parameters) throws SQLException;
}
