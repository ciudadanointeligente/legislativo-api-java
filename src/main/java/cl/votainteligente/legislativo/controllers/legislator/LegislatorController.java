package cl.votainteligente.legislativo.controllers.legislator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.service.legislator.LegislatorService;

import com.google.gson.Gson;

@Controller
public class LegislatorController {
	private Gson gson = new Gson();

	@Autowired
	LegislatorService service;

	@RequestMapping(params = { "id" }, value = "legislator/period.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getLegislatorById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return gson.toJson(service.getLegislator(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
