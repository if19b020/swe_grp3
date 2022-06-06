package DataSourceLayer;

import businesslayer.ConfigurationManager;
import businesslayer.Tour;
import businesslayer.TourLog;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseConnection implements IDataBaseConnection {

    private final String connectionString;


    public DataBaseConnection(String connectionString) {
        this.connectionString = connectionString;
    }

    private Connection CreateConnection() throws SQLException, FileNotFoundException {
        String user = ConfigurationManager.GetConfigProperty("Username");
        String password = ConfigurationManager.GetConfigProperty("Password");
        try {
            return DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Establishing database connection failed.");
    }

    @Override
    public int InsertNew(String sqlQuery, ArrayList<Object> parameters) throws SQLException {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new SQLException("Creating data failed, no ID obtained. " + sqlQuery);
    }

    @Override
    public <T> List<T> TourReader(String sqlQuery, Class<T> tourType) throws SQLException {
        try (Connection connection = CreateConnection();
             Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery(sqlQuery);
            if (tourType.getTypeName().equals(Tour.class.getName())) {
                return (List<T>) QueryTourItemDataFromResultSet(result);
            }
            if (tourType.getTypeName().equals(TourLog.class.getName())) {
                return (List<T>) QueryTourLogDataFromResultSet(result);
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new SQLException("Reading data failed. " + sqlQuery);
    }

    @Override
    public <T> List<T> TourReader(String sqlQuery, ArrayList<Object> parameters, Class<T> tourType) throws SQLException {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

            // dynamically add parameter
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }

            ResultSet result = preparedStatement.executeQuery();
            if (tourType.getTypeName().equals(Tour.class.getName())) {
                return (List<T>) QueryTourItemDataFromResultSet(result);
            }
            if (tourType.getTypeName().equals(TourLog.class.getName())) {
                return (List<T>) QueryTourLogDataFromResultSet(result);
            }

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new SQLException("Creating data failed, no ID obtained. " + sqlQuery);
    }

    @Override
    public <T> void delete(String sqlQuery, Integer id) {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateItem(String sql_update_by_id, ArrayList<Object> parameters) throws SQLException {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql_update_by_id)) {

            // dynamically add parameter
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }
            preparedStatement.execute();
            return;
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new SQLException("Updating data failed, no ID obtained. " + sql_update_by_id);
    }

    @Override
    public void UpdateLog(String sql_update_by_id, ArrayList<Object> parameters) throws SQLException {
        try (Connection connection = CreateConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql_update_by_id)) {

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setString(i + 1, parameters.get(i).toString());
            }
            preparedStatement.execute();
            return;
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new SQLException("Updating data failed, no ID obtained. " + sql_update_by_id);
    }


    private List<Tour> QueryTourItemDataFromResultSet(ResultSet result) throws SQLException {
        List<Tour> tourItemList = new ArrayList<Tour>();
        while (result.next()) {
            tourItemList.add(new Tour(
                    result.getInt("Id"),
                    result.getString("Name"),
                    result.getString("Description"),
                    result.getString("Distance"),
                    result.getString("From"),
                    result.getString("To")
            ));
        }
        return tourItemList;
    }

    private List<TourLog> QueryTourLogDataFromResultSet(ResultSet result) throws SQLException {
        List<TourLog> tourLogList = new ArrayList<TourLog>();
        while (result.next()) {
            tourLogList.add(new TourLog(
                    result.getInt("Id"),
                    result.getString("Date"),
                    result.getString("Report"),
                    result.getString("Distance"),
                    result.getString("Time"),
                    result.getString("Rating"),
                    result.getString("Weather"),
                    result.getString("Speed")
            ));
        }
        return tourLogList;
    }
}
