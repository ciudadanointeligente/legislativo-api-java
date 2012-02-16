package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.common.Constants;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.ChamberAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.DO.ChamberDO;
import cl.votainteligente.legislativo.service.ChamberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("chamber")
@Controller
public class ChamberController implements ChamberAPI {

	@Autowired
	ChamberService service;

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.ChamberAPI#getAll(int, int)
	 */
	@RequestMapping(value = "chamber/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<ChamberDO> getAll(
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return service.getAllChambersDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.ChamberAPI#getById(long)
	 */
	@RequestMapping(value = "chamber/any", method = RequestMethod.GET)
	@ResponseBody
	public final ChamberDO getById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Chamber chamber = service.getById(id);

			if(chamber == null) {
				throw new ResourceNotFoundException();
			}

			return chamber.asDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
