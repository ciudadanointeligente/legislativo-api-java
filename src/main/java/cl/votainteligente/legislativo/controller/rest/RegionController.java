package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
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
	RegionService regionService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.RegionAPI#getAll(int,int)
	 */
	@RequestMapping(value = "region/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<Region> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return regionService.getAllRegions(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.RegionAPI#findRegionsByName(String, int, int)
	 */
	@RequestMapping(params = { "name" }, value = "region/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<Region> findRegionsByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return regionService.findRegionsByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @seecl.votainteligente.legislativo.controller.rest.RegionAPI#getRegionById(long)
	 */
	@RequestMapping(params = { "id" }, value = "region/any", method = RequestMethod.GET)
	@ResponseBody
	public final Region getRegionById(@RequestParam(value = "id", required = true) final long id) {
		try {
			Region region = regionService.getRegion(id);

			if (region == null) {
				throw new ResourceNotFoundException();
			}

			return region;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
