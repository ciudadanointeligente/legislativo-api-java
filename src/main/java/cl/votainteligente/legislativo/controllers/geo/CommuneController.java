package cl.votainteligente.legislativo.controllers.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Commune;
import cl.votainteligente.legislativo.model.domainobjects.CommuneDO;
import cl.votainteligente.legislativo.service.geo.CommuneService;

@Controller
public class CommuneController {

	@Autowired
	CommuneService service;

	@RequestMapping(value = "geo/commune/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<CommuneDO> getAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.getAllCommuneDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "name" }, value = "geo/commune/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<CommuneDO> findCommunesByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.findCommuneDOsByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "geo/commune/any", method = RequestMethod.GET)
	@ResponseBody
	public final Commune getCommuneById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return service.getCommune(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
