package cl.votainteligente.legislativo.controllers.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.service.bill.BillService;
import com.google.gson.Gson;

@Controller
public class BillController {
	private Gson gson = new Gson();

	@Autowired
	BillService service;

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
}
