package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.RegionAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.Region;
import cl.votainteligente.legislativo.service.RegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("region")
@Controller
public class RegionController implements RegionAPI {
	@Autowired
	RegionService service;

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.RegionAPI#getAll(int,int)
	 */
	@RequestMapping(value = "geo/region/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<Region> getAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.getAllRegions(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.RegionAPI#findRegionsByName(java.lang.String, int, int)
	 */
	@RequestMapping(params = { "name"}, value = "geo/region/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<Region> findRegionsByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.findRegionsByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.RegionAPI#getRegionById(long)
	 */
	@RequestMapping(params = { "id" }, value = "geo/region/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final Region getRegionById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Region r = service.getRegion(id);
			if (r == null)
				throw new ResourceNotFoundException();
			return r;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}