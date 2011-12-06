package cl.votainteligente.legislativo.controllers.bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.BadRequestException;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.service.bill.BillService;
import cl.votainteligente.legislativo.service.person.PersonService;

import com.google.gson.Gson;

@Controller
public class BillController {
	private Gson gson = new Gson();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	BillService service;

	@Autowired
	PersonService personService;

	@RequestMapping(value = "bill/all.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getAll() {
		try {
			return gson.toJson(service.getAllBillDOs());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/any.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getBillById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return gson.toJson(service.getBill(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "bill/dateRange.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getDateRange(
			@RequestParam(value = "from", required = true) final String fromString,
			@RequestParam(value = "to", required = true) final String toString) {
		try {
			Date from = dateFormat.parse(fromString);
			Date to = dateFormat.parse(toString);
			return gson.toJson(service.getByDateRange(from, to));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	@RequestMapping(params = { "stage_id" }, value = "bill/stage.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getBillsByStage(
			@RequestParam(value = "stage_id", required = true) final long stage_id) {
		try {
			return gson.toJson(service.getByStage(stage_id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/author.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getByAuthors(
			@RequestParam(value = "id", required = true) final long author_id) {
		try {
			Person p = personService.getPerson(author_id);
			if (p == null)
				throw new ResourceNotFoundException();
			return gson.toJson(service.getByAuthor(p));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}