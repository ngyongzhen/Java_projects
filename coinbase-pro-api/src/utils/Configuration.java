package utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Singleton class Holds the config.properties values for referencing
 * 
 * @author Yongzhen
 *
 */
public class Configuration {
	private final Logger logger = Logger.getLogger("Configuration");
	private final Properties properties;
	private static Configuration configuration = null;

	public static synchronized Configuration getInstance() {
		if (configuration == null) {
			configuration = new Configuration("config.properties");
		}
		return configuration;
	}

	// Private constructor
	private Configuration(String filename) {
		properties = new Properties();
		try {
			loadProperties(filename);
		} catch (IOException ioException) {
			logger.warning("Unable to find config file ['" + filename + "'] in classpath");
		}
	}

	private void loadProperties(String filename) throws IOException {
		// Always clear before loading file
		properties.clear();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(filename);
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				logger.warning("Unable to find config file ['" + filename + "'] in classpath");
			}
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
	}

	public String getPropertyValue(String property) {
		return getPropertyValue(property, null);
	}

	public String getPropertyValue(String property, String defaultValue) {
		String value = properties.getProperty(property);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * Returns defaultValue is property not found.
	 * Returns defaultValue if property value cannot be parsed to integer.
	 * Returns property value in integer otherwise.
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	public int getPropertyValueAsInt(String property, int defaultValue) {
		String value = properties.getProperty(property);
		if (value == null) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
