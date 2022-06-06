package businesslayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    public static String GetConfigProperty(String propertyName) throws FileNotFoundException {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = new FileInputStream(propFileName);

        try {
            prop.load(inputStream);
            return prop.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileNotFoundException(propFileName + " was not found.");
    }
}
