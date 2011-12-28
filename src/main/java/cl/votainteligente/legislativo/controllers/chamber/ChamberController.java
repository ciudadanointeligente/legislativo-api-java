package cl.votainteligente.legislativo.controllers.chamber;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.domainobjects.ChamberDO;
import cl.votainteligente.legislativo.service.chamber.ChamberService;

@Path("chamber")
@Controller
public class ChamberController implements ChamberAPI {

	@Autowired
	ChamberService service;

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controllers.chamber.ChamberAPI#getAll(int, int)
	 */
	@RequestMapping(value = "chamber/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<ChamberDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return service.getAllChambersDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controllers.chamber.ChamberAPI#getById(long)
	 */
	@RequestMapping(value = "chamber/any", method = RequestMethod.GET)
	@ResponseBody
	public final ChamberDO getById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Chamber c = service.getById(id);
			if(c == null)
				throw new ResourceNotFoundException();
			return c.asDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
