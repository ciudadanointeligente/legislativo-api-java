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
import cl.votainteligente.legislativo.service.geo.CircunscriptionService;

import com.google.gson.Gson;

@Controller
public class CircunscriptionController {
	private Logger logger = Logger.getLogger(CircunscriptionController.class);
	private Gson gson = new Gson();

	@Autowired
	CircunscriptionService service;

	@RequestMapping(value = "geo/circunscription/all.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getAll() {
		try {
			return gson.toJson(service.getAllCircunscriptionDOs());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "name" }, value = "geo/circunscription/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String findCircunscriptionsByName(
			@RequestParam(value = "name", required = true) final String name) {
		try {
			return gson.toJson(service.findCircunscriptionDOsByName(name));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "geo/circunscription/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getCircunscriptionById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return gson.toJson(service.getCircunscriptionDO(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "region_id" }, value = "geo/circunscription/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getCircunscriptionByRegionId(
			@RequestParam(value = "region_id", required = true) final long id) {
		try {
			return gson.toJson(service.getAllCircunscriptionDOsByRegion(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
