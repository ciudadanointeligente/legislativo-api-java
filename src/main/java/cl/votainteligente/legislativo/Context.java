package cl.votainteligente.legislativo;

import cl.votainteligente.legislativo.service.PersonService;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class Context {
	private static WebApplicationContext webApplicationContext = ContextLoaderListener.getCurrentWebApplicationContext();

	public static PersonService getPersonService() {
		return webApplicationContext.getBean(PersonService.class);
	}
}
