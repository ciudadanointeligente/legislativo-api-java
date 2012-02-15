package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.DebateAPI;
import cl.votainteligente.legislativo.exception.BadRequestException;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.DO.DebateDO;
import cl.votainteligente.legislativo.model.DO.DebateDetailedDO;
import cl.votainteligente.legislativo.service.BillService;
import cl.votainteligente.legislativo.service.DebateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Path;

@Path("debate")
@Controller
public class DebateController implements DebateAPI {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationProperties.getProperty("controller.date.format"));

	@Autowired
	DebateService debateService;
	@Autowired
	BillService billService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.DebateAPI#getDebateById(long)
	 */
	@Override
	@RequestMapping(params = { "id" }, value = "debate/any", method = RequestMethod.GET)
	@ResponseBody
	public final DebateDetailedDO getDebateById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			DebateDetailedDO debate = debateService.getDebateDetailedDO(id);

			if (debate == null) {
				throw new ResourceNotFoundException();
			}

			return debate;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.DebateAPI#getDateRange(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	@RequestMapping(value = "debate/dateRange", method = RequestMethod.GET)
	@ResponseBody
	public final Page<DebateDO> getDateRange(
			@RequestParam(value = "from", required = true) final String from,
			@RequestParam(value = "to", required = true) final String to,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Date startDAte = dateFormat.parse(from);
			Date endDate = dateFormat.parse(to);
			Page<DebateDO> resultPage = debateService.getDebateByDateRange(startDAte, endDate, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.DebateAPI#getDebateByBill(long, int, int)
	 */
	@Override
	@RequestMapping(params = { "id" }, value = "debate/bill", method = RequestMethod.GET)
	@ResponseBody
	public final Page<DebateDO> getDebateByBill(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Bill bill = billService.getBill(id);

			if (bill==null) {
				throw new ResourceNotFoundException();
			}

			return debateService.getDebateByBill(bill, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
