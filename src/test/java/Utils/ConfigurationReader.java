package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    public static Properties properties;

    static {
        String path = "src/configuration.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);

    }
}
