package businesslayer;

import com.example.tourplanner.Tour;
import com.example.tourplanner.TourLog;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfManager {
    public void CreatePdfForSingleTour(Tour tour, ObservableList<TourLog> tourLogs) {
        Document document = new Document();
        String name = tour.getName();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("PDF/" + name + ".pdf"));
            document.open();

            Paragraph tourName = new Paragraph("Tour: " + name);
            tourName.setAlignment(Element.ALIGN_CENTER);
            document.add(tourName);
            document.add(new Paragraph(tour.getFullDescription()));

            Image image = Image.getInstance("Images/" + name + ".jpg");
            image.scaleAbsolute(500f, 250f);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);

            Paragraph logs = new Paragraph("\nLogs");
            logs.setAlignment(Element.ALIGN_CENTER);
            document.add(logs);

            for (TourLog tourLog : tourLogs) {
                document.add(new Paragraph("Date: " + tourLog.getDate()));
                document.add(new Paragraph("Report: " + tourLog.getReport()));
                document.add(new Paragraph("Distance: " + tourLog.getDistance()));
                document.add(new Paragraph("Time: " + tourLog.getTime()));
                document.add(new Paragraph("Rating: " + tourLog.getRating()));
                document.add(new Paragraph("Weather: " + tourLog.getWeather()));
                document.add(new Paragraph("Speed: " + tourLog.getSpeed()));
                document.add(new Paragraph("\n"));
            }
            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
