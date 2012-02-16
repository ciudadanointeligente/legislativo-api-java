package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.SessionCommissionAPI;
import cl.votainteligente.legislativo.exception.BadRequestException;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.SessionCommission;
import cl.votainteligente.legislativo.model.DO.SessionCommissionDO;
import cl.votainteligente.legislativo.model.DO.SessionCommissionDetailedDO;
import cl.votainteligente.legislativo.service.SessionCommissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Path;

@Path("sessionCommission")
@Controller
public class SessionCommissionController implements SessionCommissionAPI {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationProperties.getProperty("controller.date.format"));

	@Autowired
	SessionCommissionService sessionCommissionService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SessionCommissionAPI#getAll(int, int)
	 */
	@RequestMapping(value = "sessionCommission/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SessionCommissionDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return sessionCommissionService.getAllSessionCommissionDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SessionCommissionAPI#getById(long)
	 */
	@RequestMapping(value = "sessionCommission/any", method = RequestMethod.GET)
	@ResponseBody
	public final SessionCommissionDetailedDO getById(@RequestParam(value = "id", required = true) final long id) {
		try {
			SessionCommission sessionCommission = sessionCommissionService.getSessionCommission(id);

			if (sessionCommission == null) {
				throw new ResourceNotFoundException();
			}

			return sessionCommission.asSessionCommissionDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SessionCommissionAPI#getDateRange(String, String, int, int)
	 */
	@Override
	@RequestMapping(value = "sessionCommission/dateRange", method = RequestMethod.GET)
	@ResponseBody
	public Page<SessionCommissionDO> getDateRange(
			@RequestParam(value = "from", required = true) final String from,
			@RequestParam(value = "to", required = true) final String to,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Date startDate = dateFormat.parse(from);
			Date endDate = dateFormat.parse(to);
			return sessionCommissionService.getByDateRange(startDate, endDate, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SessionCommissionAPI#getByLegislature(long, int, int)
	 */
	@Override
	@RequestMapping(value = "sessionCommission/legislature", method = RequestMethod.GET)
	@ResponseBody
	public Page<SessionCommissionDO> getByLegislature(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return sessionCommissionService.getByLegislature(id, page, perPage);
		} catch (ServiceException e) {
			throw new ServerErrorException();
		}
	}
}
