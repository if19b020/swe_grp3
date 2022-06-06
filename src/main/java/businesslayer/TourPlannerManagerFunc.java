package businesslayer;

import DataSourceLayer.DataFactory;
import DataSourceLayer.dao.ITourDao;
import DataSourceLayer.dao.ITourLogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;

public class TourPlannerManagerFunc implements TourPlannerManager {
    Logger logger = LogManager.getLogger(TourPlannerManager.class);

    @Override
    public String GetMapQuest(String name, String from, String to) throws IOException, JSONException, InterruptedException {
        MapQuest mapquest = new MapQuest();
        return mapquest.startMapQuest(name, from, to);
    }

    @Override
    public List<Tour> GetItems() throws SQLException {
        ITourDao tourItemDAO = DataFactory.CreateTourItemDAO();
        return tourItemDAO.GetItems();
    }

    public List<TourLog> GetLogs(Tour tourItem) throws SQLException {
        ITourLogDao tourLogDAO = DataFactory.CreateTourLogDAO();
        return tourLogDAO.GetLogsForItem(tourItem);
    }

    @Override
    public void saveTourToPdf(Tour tour, ObservableList<TourLog> tourLogs) {
        PdfManager pdfCreator = new PdfManager();
        pdfCreator.CreatePdfForSingleTour(tour, tourLogs);
        logger.info("Tour info successfully saved to PDF file.");
    }

    @Override
    public void DeleteTourImage(String imagePath) {
        Path path = Paths.get(imagePath);
        try {
            Files.delete(path);
            logger.info("Tour image was successfully removed.");
        } catch (IOException e) {
            logger.error("Tour image couldn't be deleted.");
        }
    }

    @Override
    public List<Tour> Search(String itemName, boolean caseSensitive) throws SQLException {
        List<Tour> items = GetItems();

        if (caseSensitive) {
            return items
                    .stream()
                    .filter(x -> x.getName().contains(itemName))
                    .collect(Collectors.toList());
        }
        return items
                .stream()
                .filter(x -> x.getName().toLowerCase().contains(itemName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void CreateTourItem(String name, String description, String distance, String from, String to) throws SQLException {
        ITourDao tourItemDAO = DataFactory.CreateTourItemDAO();
        tourItemDAO.AddNewItem(name, description, distance, from, to);
        logger.info("Tour was successfully created.");
    }

    @Override
    public void UpdateTourItem(Integer id, String name, String description, String distance, String start, String end) throws SQLException {
        ITourDao tourItemDAO = DataFactory.CreateTourItemDAO();
        tourItemDAO.UpdateTourById(id, name, description, distance, start, end);
        logger.info("Tour was successfully changed.");
    }

    @Override
    public void CreateTourLog(TourLog log, Tour item) throws SQLException {
        InputValidator inputValidation = new InputValidator();

        if(     !inputValidation.containsOnlyLettersOrIsEmpty(log.getWeather()) || !inputValidation.checksIfTimeFormatIsCorrect(log.getTime())
                || !inputValidation.checksIfDateFormatIsCorrect(log.getDate()) || !inputValidation.containsOnlyNumbersforRating(log.getRating())
                || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(log.getSpeed())){
            logger.info("Log couldn't be created because the inputs contained wrong characters.");
            return;
        }
        ITourLogDao tourLogDAO = DataFactory.CreateTourLogDAO();
        tourLogDAO.AddNewItemLog(log, item);
        logger.info("Log was successfully created.");
    }

    @Override
    public boolean validateAddressInput(String start, String finish) {
        InputValidator inputValidation = new InputValidator();
        return inputValidation.containsLettersThatAreOptionallyFollowedByNumbers(start, finish);
    }

    @Override
    public void UpdateLogItem(TourLog genLog, Integer id) throws SQLException {
        InputValidator inputValidation = new InputValidator();
        if(     !inputValidation.containsOnlyLettersOrIsEmpty(genLog.getWeather()) || !inputValidation.checksIfTimeFormatIsCorrect(genLog.getTime())
                || !inputValidation.checksIfDateFormatIsCorrect(genLog.getDate()) || !inputValidation.containsOnlyNumbersforRating(genLog.getRating())
                || !inputValidation.containsNumbersWithDecimalPlacesOrIsEmpty(genLog.getSpeed())){
            logger.info("Log couldn't be changed because the inputs contained wrong characters.");
            return;
        }
        ITourLogDao tourLogDAO = DataFactory.CreateTourLogDAO();
        tourLogDAO.UpdateLogById(genLog, id);
        logger.info("Log was successfully changed.");
    }

    @Override
    public void DeleteTour(Tour item) {
        ITourDao tourItemDAO = DataFactory.CreateTourItemDAO();
        try {
            tourItemDAO.DeleteById(item.getId());
            logger.info("Tour was deleted.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Some error occurred trying to delete a Tour.");
        }
        DeleteTourImage("Images/" + item.getName() + ".jpg");
    }

    @Override
    public void DeleteLog(TourLog log) {
        ITourLogDao tourLogDAO = DataFactory.CreateTourLogDAO();
        try {
            tourLogDAO.DeleteById(log.getId());
            logger.info("Log was deleted.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Some error occurred trying to delete a Log.");
        }
    }

    @Override
    public Tour GetItem(Integer id) throws SQLException {
        return DataFactory.CreateTourItemDAO().FindById(id);
    }

}
