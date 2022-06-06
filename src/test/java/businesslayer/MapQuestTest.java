package businesslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class MapQuestTest {

    private MapQuest mapQuest;
    @BeforeEach
    public void setUp() {
        mapQuest = new MapQuest();
    }

    @Test
    public void testIfMapquestGeneratesDistance() throws IOException, InterruptedException {
        String name = "name";
        String from = "Graz";
        String to = "Wien";
        assertFalse(mapQuest.startMapQuest(name, from, to).isEmpty());
        Files.delete(Path.of("Images/" + name + ".jpg"));
    }

    @Test
    public void testIfMapquestGeneratesImageFile() throws IOException, InterruptedException {
        String name = "name";
        String filePath = "Images/" + name + ".jpg";
        Path path = Paths.get(filePath);
        File file = new File (filePath);
        String from = "Graz";
        String to = "Wien";
        assertFalse(file.exists());
        mapQuest.startMapQuest(name, from, to);
        assertTrue(file.exists());
        Files.delete(path);
    }


}