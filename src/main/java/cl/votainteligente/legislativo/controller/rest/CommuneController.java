package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.CommuneAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.Commune;
import cl.votainteligente.legislativo.model.DO.CommuneDO;
import cl.votainteligente.legislativo.service.CommuneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("commune")
@Controller
public class CommuneController implements CommuneAPI {

	@Autowired
	CommuneService service;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.CommuneAPI#getAll(int,int)
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.CommuneAPI#findCommunesByName(java.lang.String, int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.CommuneAPI#getCommuneById(long)
	 */
	@RequestMapping(params = { "id" }, value = "geo/commune/any", method = RequestMethod.GET)
	@ResponseBody
	public final CommuneDO getCommuneById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Commune c = service.getCommune(id);
			if (c == null)
				throw new ResourceNotFoundException();
			return c.asDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
