package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.CircunscriptionAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.DO.CircunscriptionDO;
import cl.votainteligente.legislativo.service.CircunscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("circunscription")
@Controller
public class CircunscriptionController implements CircunscriptionAPI {

	@Autowired
	CircunscriptionService service;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.CircunscriptionAPI#getAll(int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.CircunscriptionAPI#findCircunscriptionsByName(java.lang.String, int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.CircunscriptionAPI#getCircunscriptionById(long)
	 */
	@RequestMapping(params = { "id" }, value = "geo/circunscription/any", method = RequestMethod.GET)
	@ResponseBody
	public final CircunscriptionDO getCircunscriptionById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Circunscription c = service.getCircunscription(id);
			if (c == null)
				throw new ResourceNotFoundException();
			return c.asDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.CircunscriptionAPI#getCircunscriptionByRegionId(long, int, int)
	 */
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