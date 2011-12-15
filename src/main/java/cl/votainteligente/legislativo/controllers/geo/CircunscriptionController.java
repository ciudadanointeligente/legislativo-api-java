package cl.votainteligente.legislativo.controllers.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.domainobjects.Page;
import cl.votainteligente.legislativo.model.domainobjects.CircunscriptionDO;
import cl.votainteligente.legislativo.service.geo.CircunscriptionService;

@Controller
public class CircunscriptionController {

	@Autowired
	CircunscriptionService service;

	@RequestMapping(value = "geo/circunscription/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<CircunscriptionDO> getAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			Page<CircunscriptionDO> resultPage = service
					.getAllCircunscriptionDOs(page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "name" }, value = "geo/circunscription/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<CircunscriptionDO> findCircunscriptionsByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			Page<CircunscriptionDO> resultPage = service
					.findCircunscriptionDOsByName(name, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "geo/circunscription/any", method = RequestMethod.GET)
	@ResponseBody
	public final CircunscriptionDO getCircunscriptionById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return service.getCircunscriptionDO(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "region_id" }, value = "geo/circunscription/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<CircunscriptionDO> getCircunscriptionByRegionId(
			@RequestParam(value = "region_id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			Page<CircunscriptionDO> resultPage = service
					.getAllCircunscriptionDOsByRegion(id, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
