package cl.votainteligente.legislativo.common;

import cl.votainteligente.legislativo.ApplicationProperties;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat(ApplicationProperties.getProperty("controller.date.format"));
		String formattedDate = formatter.format(value);
		gen.writeString(formattedDate);
	}
}
