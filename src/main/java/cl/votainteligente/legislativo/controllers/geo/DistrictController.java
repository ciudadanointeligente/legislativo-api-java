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
import cl.votainteligente.legislativo.service.geo.DistrictService;

import com.google.gson.Gson;

@Controller
public class DistrictController {
	private Logger logger = Logger.getLogger(DistrictController.class);
	private Gson gson = new Gson();

	@Autowired
	DistrictService service;

	@RequestMapping(value = "geo/district/all.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getAll() {
		try {
			return gson.toJson(service.getAllDistrictDOs());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "name" }, value = "geo/district/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String findDistrictsByName(
			@RequestParam(value = "name", required = true) final String name) {
		try {
			return gson.toJson(service.findDistrictDOsByName(name));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "geo/district/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getDistrictById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return gson.toJson(service.getDistrictDO(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
