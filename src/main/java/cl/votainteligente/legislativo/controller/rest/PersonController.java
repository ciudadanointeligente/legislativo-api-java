package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.common.Constants;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.PersonAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.model.DO.PersonDetailedDO;
import cl.votainteligente.legislativo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("person")
@Controller
public class PersonController implements PersonAPI {
	@Autowired
	PersonService personService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.PersonAPI#getAll(int, int)
	 */
	@Override
	@RequestMapping(value = "person/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getAll(
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return personService.getAllPersonDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.PersonAPI#findPersonByFirstName(String, int, int)
	 */
	@Override
	@RequestMapping(params = { "firstName" }, value = "person/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> findPersonByFirstName(
			@RequestParam(value = "firstName", required = true) final String firstName,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return personService.findPersonsByFirstName(firstName, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.PersonAPI#findPersonByLastName(String, int, int)
	 */
	@Override
	@RequestMapping(params = { "lastName" }, value = "person/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> findPersonByLastName(
			@RequestParam(value = "lastName", required = true) final String lastName,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {

		try {
			return personService.findPersonsByLastName(lastName, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @seecl.votainteligente.legislativo.controller.rest.PersonAPI#getPersonById(long)
	 */
	@Override
	@RequestMapping(params = { "id" }, value = "person/any", method = RequestMethod.GET)
	@ResponseBody
	public final PersonDetailedDO getPersonById(@RequestParam(value = "id", required = true) final long id) {
		try {
			Person person = personService.getPerson(id);

			if (person == null) {
				throw new ResourceNotFoundException();
			}

			return person.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}