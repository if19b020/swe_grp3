package DataSourceLayer;

import DataSourceLayer.dao.ITourLogDao;
import com.example.tourplanner.Tour;
import com.example.tourplanner.TourLog;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourLogDao implements ITourLogDao {
    private final String SQL_FIND_BY_ID = "SELECT * FROM \"tourlog\" WHERE \"id\"=CAST(? AS INTEGER);";
    private final String SQL_DELETE_BY_ID = "DELETE FROM \"tourlog\" WHERE \"id\"=CAST(? AS INTEGER);";
    private final String SQL_UPDATE_BY_ID = "UPDATE \"tourlog\" set \"date\" = ?, \"report\" = ?, \"distance\" = ?, \"time\" = ?, \"rating\" = ?," +
            " \"weather\" = ?, \"speed\" = ?, \"altitude\" = ?, \"difficulty\" = ?, \"calories\" = ? WHERE \"id\"=CAST(? AS INTEGER);";
    private final String SQL_FIND_BY_TOURITEM = "SELECT * FROM \"tourlog\" WHERE \"fk_TourId\"=CAST(? AS INTEGER);";
    private final String SQL_INSERT_NEW_ITEMLOG = "INSERT INTO \"tourlog\" (\"fk_TourId\", \"date\", \"report\", \"distance\", " +
            "\"time\", \"rating\", \"weather\", \"speed\", \"altitude\", \"difficulty\", \"calories\") VALUES (CAST(? AS INTEGER), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final IDataBaseConnection database;

    public TourLogDao() throws FileNotFoundException {
        this.database = DataFactory.GetDatabase();
    }

    @Override
    public void FindById(Integer logId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(logId);

        List<TourLog> tourItems = database.TourReader(SQL_FIND_BY_ID, parameters, TourLog.class);
        tourItems.stream().findFirst().get();
    }

    @Override
    public void DeleteById(Integer itemId) throws SQLException {
        database.delete(SQL_DELETE_BY_ID, itemId);
    }

    @Override
    public void UpdateLogById(TourLog genLog, Integer id) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(genLog.getDate());
        parameters.add(genLog.getReport());
        parameters.add(genLog.getDistance());
        parameters.add(genLog.getTime());
        parameters.add(genLog.getRating());
        parameters.add(genLog.getWeather());
        parameters.add(genLog.getSpeed());
        parameters.add(id);

        database.UpdateLog(SQL_UPDATE_BY_ID, parameters);
        FindById(id);
    }

    @Override
    public void AddNewItemLog(TourLog log, Tour tourItem) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(tourItem.getId());
        parameters.add(log.getDate());
        parameters.add(log.getReport());
        parameters.add(log.getDistance());
        parameters.add(log.getTime());
        parameters.add(log.getRating());
        parameters.add(log.getWeather());
        parameters.add(log.getSpeed());

        int resultId = database.InsertNew(SQL_INSERT_NEW_ITEMLOG, parameters);
        FindById(resultId);
    }

    @Override
    public List<TourLog> GetLogsForItem(Tour item) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(item.getId());

        return database.TourReader(SQL_FIND_BY_TOURITEM, parameters, TourLog.class);
    }
}
