package DataSourceLayer;

import DataSourceLayer.dao.ITourDao;
import com.example.tourplanner.Tour;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDao implements ITourDao {
    private final String SQL_FIND_BY_ID = "SELECT * FROM \"tour\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_DELETE_BY_ID = "DELETE FROM \"tour\" WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_UPDATE_BY_ID = "UPDATE \"tour\" set \"Name\" = ?, \"Description\" = ?, \"Distance\" = ?, \"From\" = ?, \"To\" = ? WHERE \"Id\"=CAST(? AS INTEGER);";
    private final String SQL_GET_ALL_ITEMS = "SELECT * FROM \"tour\";";
    private final String SQL_INSERT_NEW_ITEM = "INSERT INTO \"tour\" (\"Name\", \"Description\", \"Distance\", \"From\", \"To\") VALUES (?, ?, ?, ?, ?);";
    private final IDataBaseConnection database;

    public TourDao() throws FileNotFoundException {
        database = DataFactory.GetDatabase();
    }

    @Override
    public Tour FindById(Integer itemId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(itemId);

        List<Tour> tourItems = database.TourReader(SQL_FIND_BY_ID, parameters, Tour.class);
        return tourItems.stream().findFirst().get();
    }

    @Override
    public void DeleteById(Integer itemId) throws SQLException {
        database.delete(SQL_DELETE_BY_ID, itemId);
    }

    @Override
    public void UpdateTourById(Integer itemId, String name, String description, String distance, String from, String to) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(name);
        parameters.add(description);
        parameters.add(distance);
        parameters.add(from);
        parameters.add(to);
        parameters.add(itemId);

        database.UpdateItem(SQL_UPDATE_BY_ID, parameters);
        FindById(itemId);
    }

    @Override
    public void AddNewItem(String name, String description, String distance, String from, String to) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(name);
        parameters.add(description);
        parameters.add(distance);
        parameters.add(from);
        parameters.add(to);

        int resultId = database.InsertNew(SQL_INSERT_NEW_ITEM, parameters);
        FindById(resultId);
    }

    @Override
    public List<Tour> GetItems() throws SQLException {
        return database.TourReader(SQL_GET_ALL_ITEMS, Tour.class);
    }
}
