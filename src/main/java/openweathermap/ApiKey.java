package openweathermap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Retrieves an "apikey" either from "apikey.properties" or from environment variables.
 */
public class ApiKey {

    private final Properties properties = new Properties();

    public ApiKey() {
        try {
            // Load properties from file
            InputStream in = ApiKey.class.getResourceAsStream("/apikey.properties");
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException e) {
            // this will fail when run on a machine without the Properties file
        }
    }

    @Override
    public String toString() {
        // Check if the property exists in the properties file
        String propertyValue = properties.getProperty("apikey");
        if (propertyValue != null) {
            return propertyValue;
        }

        // If not found, check environment variables
        return System.getProperty("apikey");
    }
}
