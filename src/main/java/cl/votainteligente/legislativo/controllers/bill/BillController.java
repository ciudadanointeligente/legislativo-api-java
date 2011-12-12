package cl.votainteligente.legislativo.controllers.bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.BadRequestException;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;
import cl.votainteligente.legislativo.service.bill.BillService;
import cl.votainteligente.legislativo.service.matter.MatterService;
import cl.votainteligente.legislativo.service.person.PersonService;
import net.sf.json.*;

@Controller
public class BillController {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	BillService service;

	@Autowired
	PersonService personService;

	@Autowired
	MatterService matterService;

	private ModelAndView generateResponse(Page<BillDO> resultPage, HttpServletRequest request) {
		String jsonString = JSONArray.fromObject(resultPage).toString(2);
		request.getSession().setAttribute("json",jsonString);
		return new ModelAndView("/views/json.jsp");
	}

	@RequestMapping(value = "bill/all.json", method = RequestMethod.GET)
	public final ModelAndView getAll(HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request, "perPage", 10);
			Page<BillDO> resultPage = service.getAllBillDOs(page, perPage);
			return generateResponse(resultPage, request);
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
			return JSONObject.fromObject(service.getBill(id)).toString(2);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "bill/dateRange.json", method = RequestMethod.GET)
	public final ModelAndView getDateRange(
			@RequestParam(value = "from", required = true) final String fromString,
			@RequestParam(value = "to", required = true) final String toString,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request, "perPage", 10);
			Date from = dateFormat.parse(fromString);
			Date to = dateFormat.parse(toString);
			Page<BillDO> resultPage = service.getByDateRange(from, to, page, perPage);
			return generateResponse(resultPage, request);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	@RequestMapping(params = { "stage_id" }, value = "bill/stage.json", method = RequestMethod.GET)
	public final ModelAndView getBillsByStage(
			@RequestParam(value = "stage_id", required = true) final long stage_id,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request, "perPage", 10);
			Page<BillDO> resultPage = service.getByStage(stage_id, page, perPage);
			return generateResponse(resultPage, request);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/author.json", method = RequestMethod.GET)
	public final ModelAndView getByAuthors(
			@RequestParam(value = "id", required = true) final long author_id,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request, "perPage", 10);
			Person p = personService.getPerson(author_id);
			if (p == null)
				throw new ResourceNotFoundException();
			Page<BillDO> resultPage = service.getByAuthor(p, page, perPage);
			return generateResponse(resultPage, request);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/matter.json", method = RequestMethod.GET)
	public final ModelAndView getByMatter(
			@RequestParam(value = "id", required = true) final long matter_id,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request, "perPage", 10);
			Matter p = matterService.getById(matter_id);
			if (p == null)
				throw new ResourceNotFoundException();
			Page<BillDO> resultPage = service.getByMatter(p, page, perPage);
			return generateResponse(resultPage, request);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}