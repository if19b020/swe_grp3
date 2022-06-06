package DataSourceLayer;

import DataSourceLayer.dao.ITourDao;
import DataSourceLayer.dao.ITourLogDao;
import businesslayer.ConfigurationManager;
import java.io.FileNotFoundException;


public class DataFactory {

    private static IDataBaseConnection database;

    public static IDataBaseConnection GetDatabase() throws FileNotFoundException {
        if (database == null) {
            database = CreateDatabase();
        }
        return database;
    }

    private static IDataBaseConnection CreateDatabase() throws FileNotFoundException {
        String connectionString = ConfigurationManager.GetConfigProperty("PostgresSQLConnectionString");
        return CreateDatabase(connectionString);
    }

    private static IDataBaseConnection CreateDatabase(String connectionString) {
        try {
            Class<DataBaseConnection> cls = (Class<DataBaseConnection>) Class.forName(DataBaseConnection.class.getName());
            return cls.getConstructor(String.class).newInstance(connectionString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ITourDao CreateTourItemDAO() {
        try {
            Class<TourDao> cls = (Class<TourDao>) Class.forName(TourDao.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ITourLogDao CreateTourLogDAO() {
        try {
            Class<TourLogDao> cls = (Class<TourLogDao>) Class.forName(TourLogDao.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}