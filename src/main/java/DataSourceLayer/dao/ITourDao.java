package DataSourceLayer.dao;

import java.sql.SQLException;
import java.util.List;
import com.example.tourplanner.Tour;
public interface ITourDao {
    Tour FindById(Integer itemId) throws SQLException;
    void DeleteById(Integer itemId) throws SQLException;
    void UpdateTourById(Integer itemId, String name, String description, String distance, String start, String end) throws SQLException;
    void AddNewItem(String name, String description, String distance, String start, String end) throws SQLException;
    List<Tour> GetItems() throws SQLException;
}
