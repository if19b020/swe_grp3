package DataSourceLayer.dao;
import businesslayer.Tour;
import businesslayer.TourLog;
import java.sql.SQLException;
import java.util.List;

public interface ITourLogDao {
    void FindById(Integer logId) throws SQLException;
    void DeleteById(Integer itemId) throws SQLException;
    void UpdateLogById(TourLog genLog, Integer id) throws SQLException;
    void AddNewItemLog(TourLog log, Tour logItem) throws SQLException;
    List<TourLog> GetLogsForItem(Tour item) throws SQLException;

}
