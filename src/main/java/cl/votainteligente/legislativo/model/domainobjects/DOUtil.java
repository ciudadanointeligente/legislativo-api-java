package cl.votainteligente.legislativo.model.domainobjects;

import java.text.SimpleDateFormat;
import java.util.Locale;

import cl.votainteligente.legislativo.ApplicationProperties;

public class DOUtil {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			ApplicationProperties.getProperty("do.date.format"), new Locale(
					ApplicationProperties.getProperty("localization.language"),
					ApplicationProperties.getProperty("localization.country")));

	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
}
