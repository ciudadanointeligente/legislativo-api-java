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
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;
import cl.votainteligente.legislativo.service.geo.DistrictService;

@Controller
public class DistrictController {

	@Autowired
	DistrictService service;

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

	@RequestMapping(params = { "name", "page", "perPage" }, value = "geo/district/any", method = RequestMethod.GET)
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

	@RequestMapping(params = { "id" }, value = "geo/district/any", method = RequestMethod.GET)
	@ResponseBody
	public final DistrictDO getDistrictById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return service.getDistrictDO(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
