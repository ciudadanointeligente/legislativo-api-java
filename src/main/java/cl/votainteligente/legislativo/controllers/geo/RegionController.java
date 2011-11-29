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
import cl.votainteligente.legislativo.service.geo.RegionService;

import com.google.gson.Gson;

@Controller
public class RegionController {
	private Logger logger = Logger.getLogger(RegionController.class);
	private Gson gson = new Gson();

	@Autowired
	RegionService service;

	@RequestMapping(value = "geo/region/all.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getAll() {
		try {
			return gson.toJson(service.getAllRegions());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "name" }, value = "geo/region/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String findRegionsByName(
			@RequestParam(value = "name", required = true) final String name) {
		try {
			return gson.toJson(service.findRegionsByName(name));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "geo/region/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getRegionById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return gson.toJson(service.getRegion(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
