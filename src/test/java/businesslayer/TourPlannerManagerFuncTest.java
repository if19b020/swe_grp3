package businesslayer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TourPlannerManagerFuncTest {

    private TourPlannerManagerFunc manager;
    @Test
    public void testIfFileexist() throws IOException, InterruptedException {

        MapQuest mapQuest = new MapQuest();
        String name = "test";
        String filePath = "Images/" + name + ".jpg";
        mapQuest.startMapQuest(name, "wien", "graz");
        File file = new File (filePath);
        assertTrue(file.exists());

    }

    @Test
    public void testDeleteTour() throws IOException, InterruptedException {

        MapQuest mapQuest = new MapQuest();
        String name = "test";
        String filePath = "Images/" + name + ".jpg";
        mapQuest.startMapQuest(name, "wien", "graz");
        File file = new File(filePath);

        manager = new TourPlannerManagerFunc();
        manager.DeleteTourImage(filePath);
        assertFalse(file.exists());
    }
}