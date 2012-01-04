package cl.votainteligente.legislativo.controllers.session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import cl.votainteligente.legislativo.exceptions.BadRequestException;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.SessionChamber;
import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDetailedDO;
import cl.votainteligente.legislativo.service.session.SessionChamberService;

@Path("sessionChamber")
@Controller
public class SessionChamberController implements SessionChamberAPI {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			ApplicationProperties.getProperty("controller.date.format"));
	@Autowired
	SessionChamberService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.votainteligente.legislativo.controllers.session.SessionAI#getAll(int,
	 * int)
	 */
	@RequestMapping(value = "sessionChamber/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SessionChamberDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return service.getAllSessionChamberDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.votainteligente.legislativo.controllers.session.SessionAI#getById(
	 * long)
	 */
	@RequestMapping(value = "sessionChamber/any", method = RequestMethod.GET)
	@ResponseBody
	public final SessionChamberDetailedDO getById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			SessionChamber s = service.getSessionChamber(id);
			if (s == null)
				throw new ResourceNotFoundException();
			return s.asSessionChamberDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}

	}

	@Override
	@RequestMapping(value = "sessionChamber/dateRange", method = RequestMethod.GET)
	@ResponseBody
	public Page<SessionChamberDO> getDateRange(
			@RequestParam(value = "from", required = true) final String fromString,
			@RequestParam(value = "to", required = true) final String toString,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Date from = dateFormat.parse(fromString);
			Date to = dateFormat.parse(toString);
			return service.getByDateRange(from, to, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	@Override
	@RequestMapping(value = "sessionChamber/legislature", method = RequestMethod.GET)
	@ResponseBody
	public Page<SessionChamberDO> getByLegislature(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return service.getByLegislature(id, page, perPage);
		} catch (ServiceException e) {
			throw new ServerErrorException();
		}
	}
}
