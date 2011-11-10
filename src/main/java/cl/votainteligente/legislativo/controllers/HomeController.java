package cl.votainteligente.legislativo.controllers;

import cl.votainteligente.legislativo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PersonService personService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView setupForm() throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("mensaje", "Hola, como estan");

		return modelAndView;
	}

}
