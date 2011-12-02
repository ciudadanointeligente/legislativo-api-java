package cl.votainteligente.legislativo.controllers.geo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.service.geo.CommuneService;

import com.google.gson.Gson;

@Controller
public class CommuneController {
	private Logger logger = Logger.getLogger(CommuneController.class);
	private Gson gson = new Gson();

	@Autowired
	CommuneService service;

	@RequestMapping(value = "geo/commune/all.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getAll() {
		try {
			return gson.toJson(service.getAllCommuneDOs());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "name" }, value = "geo/commune/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String findCommunesByName(
			@RequestParam(value = "name", required = true) final String name) {
		try {
			return gson.toJson(service.findCommuneDOsByName(name));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "geo/commune/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getCommuneById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return gson.toJson(service.getCommuneDO(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
