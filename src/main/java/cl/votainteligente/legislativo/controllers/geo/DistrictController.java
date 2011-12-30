package cl.votainteligente.legislativo.controllers.geo;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;
import cl.votainteligente.legislativo.service.geo.DistrictService;

@Path("district")
@Controller
public class DistrictController implements DistrictAPI {

	@Autowired
	DistrictService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.votainteligente.legislativo.controllers.geo.DistrictAPI#getAll(int,
	 * int)
	 */
	@RequestMapping(value = "geo/district/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<DistrictDO> getAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.getAllDistrictDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.votainteligente.legislativo.controllers.geo.DistrictAPI#
	 * findDistrictsByName(java.lang.String, int, int)
	 */
	@RequestMapping(params = { "name"}, value = "geo/district/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<DistrictDO> findDistrictsByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.findDistrictDOsByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.votainteligente.legislativo.controllers.geo.DistrictAPI#getDistrictById
	 * (long)
	 */
	@RequestMapping(params = { "id" }, value = "geo/district/any", method = RequestMethod.GET)
	@ResponseBody
	public final DistrictDO getDistrictById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			District d = service.getDistrict(id);
			if (d == null)
				throw new ResourceNotFoundException();
			return d.asDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
