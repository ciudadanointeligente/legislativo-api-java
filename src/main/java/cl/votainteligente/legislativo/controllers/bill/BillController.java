package cl.votainteligente.legislativo.controllers.bill;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.BadRequestException;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.StageDescription;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.service.bill.BillService;
import cl.votainteligente.legislativo.service.bill.StageDescriptionService;
import cl.votainteligente.legislativo.service.matter.MatterService;
import cl.votainteligente.legislativo.service.person.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BillController {
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

	@RequestMapping(params = { "id" }, value = "bill/any", method = RequestMethod.GET)
	@ResponseBody
	public final Bill getBillById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return service.getBill(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "bill/dateRange", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getDateRange(
			@RequestParam(value = "from", required = true) final String fromString,
			@RequestParam(value = "to", required = true) final String toString,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Date from = dateFormat.parse(fromString);
			Date to = dateFormat.parse(toString);
			Page<BillDO> resultPage = service.getByDateRange(from, to, page,
					perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/stage", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getBillsByStage(
			@RequestParam(value = "id", required = true) final long stage_id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			StageDescription stageDescription = stageDescriptionService
					.getById(stage_id);
			Page<BillDO> resultPage = service.getByStage(stageDescription,
					page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/author", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getByAuthors(
			@RequestParam(value = "id", required = true) final long author_id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Person p = personService.getPerson(author_id);
			if (p == null)
				throw new ResourceNotFoundException();
			Page<BillDO> resultPage = service.getByAuthor(p, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/matter", method = RequestMethod.GET)
	@ResponseBody
	public final Page<BillDO> getByMatter(
			@RequestParam(value = "id", required = true) final long matter_id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {

			Matter p = matterService.getById(matter_id);
			if (p == null)
				throw new ResourceNotFoundException();
			Page<BillDO> resultPage = service.getByMatter(p, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}