package cl.votainteligente.legislativo.controllers.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.person.PersonService;

@Controller
public class PersonController {
	@Autowired
	PersonService service;

	@RequestMapping(value = "person/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.getAllPersonDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "firstName" }, value = "person/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> findPersonByFirstName(
			@RequestParam(value = "firstName", required = true) final String firstName,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.findPersonsByFirstName(firstName, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "lastName" }, value = "person/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> findPersonByLastName(
			@RequestParam(value = "lastName", required = true) final String lastName,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {

		try {
			return service.findPersonsByLastName(lastName, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "person/any", method = RequestMethod.GET)
	@ResponseBody
	public final Person getPersonById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return service.getPerson(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

}