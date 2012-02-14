package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.BillAPI;
import cl.votainteligente.legislativo.exception.BadRequestException;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.*;
import cl.votainteligente.legislativo.model.DO.BillDO;
import cl.votainteligente.legislativo.model.DO.BillDetailedDO;
import cl.votainteligente.legislativo.model.DO.BillRoleDO;
import cl.votainteligente.legislativo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Path;

@Path("bill")
@Controller
public class BillController implements BillAPI {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			ApplicationProperties.getProperty("controller.date.format"));

	@Autowired
	BillService service;
	@Autowired
	PersonService personService;
	@Autowired
	MatterService matterService;
	@Autowired
	StageDescriptionService stageDescriptionService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.BillAPI#getAll(int, int)
	 */
	@RequestMapping(value = "bill/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Page<BillDO> resultPage = service.getAllBillDOs(page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.BillAPI#getBillById(int)
	 */
	@RequestMapping(params = { "id" }, value = "bill/any", method = RequestMethod.GET)
	@ResponseBody
	public final BillDetailedDO getBillById(
			@RequestParam(value = "id", required = true) final long billId) {
		try {
			Bill bill = service.getBill(billId);

			if (bill == null) {
				throw new ResourceNotFoundException();
			}

			return bill.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.BillAPI#getDataRange(String, String, int, int)
	 */
	@RequestMapping(value = "bill/dateRange", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getDateRange(
			@RequestParam(value = "from", required = true) final String startDate,
			@RequestParam(value = "to", required = true) final String endDate,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Date start = dateFormat.parse(startDate);
			Date end = dateFormat.parse(endDate);
			Page<BillDO> resultPage = service.getByDateRange(start, end, page, perPage);
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
	 * @see cl.votainteligente.legislativo.controller.rest.iface.BillAPI#getBillsByStage(long, int, int)
	 */
	@RequestMapping(params = { "id" }, value = "bill/stage", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getBillsByStage(
			@RequestParam(value = "id", required = true) final long stageId,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			StageDescription stageDescription = stageDescriptionService.getById(stageId);
			Page<BillDO> resultPage = service.getByStage(stageDescription, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.BillAPI#getByMatter(long, int, int)
	 */
	@RequestMapping(params = { "id" }, value = "bill/matter", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getByMatter(
			@RequestParam(value = "id", required = true) final long matterId,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {

			Matter matter = matterService.getById(matterId);

			if (matter == null) {
				throw new ResourceNotFoundException();
			}

			Page<BillDO> resultPage = service.getByMatter(matter, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.BillAPI#getBillRoleByPerson(long, int, int)
	 */
	@RequestMapping(params = { "id" }, value = "bill/author", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillRoleDO> getBillRoleByPerson(
			@RequestParam(value = "id", required = true) final long authorId,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Person person = personService.getPerson(authorId);

			if (person == null) {
				throw new ResourceNotFoundException();
			}

			Page<BillRoleDO> resultPage = service.getByAuthorRole(person, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

}