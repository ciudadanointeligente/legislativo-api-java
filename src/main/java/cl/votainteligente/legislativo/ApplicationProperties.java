package cl.votainteligente.legislativo;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationProperties implements ServletContextListener {
	private static final Logger logger = Logger
			.getLogger(ApplicationProperties.class);
	private static Properties properties;

	/*
	 * Static properties for annotations. This properties can't be loaded from a
	 * properties files because The value for annotation attribute must be a
	 * constant expression, because they are resolved at compilation time.
	 */
	public static final String CONTROLLER_PAGE_DEFAULT_VALUE = "1";
	public static final String CONTROLLER_PER_PAGE_DEFAULT_VALUE = "10";

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("Context destroyed");
		properties = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(event.getServletContext()
					.getRealPath("/WEB-INF/application.properties")));
			logger.info("Context initialized");
		} catch (Throwable ex) {
			logger.error("Failed to initialize context", ex);
		}
	}

	public static String getProperty(String key) {
		String property = properties.getProperty(key);

		if (property != null && property.length() == 0) {
			property = null;
		}

		return property;
	}
}
