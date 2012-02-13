package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.PersonAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
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
	PersonService service;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.PersonAPI#getAll(int,int)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.PersonAPI#findPersonByFirstName(java.lang.String, int, int)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.PersonAPI#findPersonByLastName(java.lang.String, int, int)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.PersonAPI#getPersonById(long)
	 */
	@Override
	@RequestMapping(params = { "id" }, value = "person/any", method = RequestMethod.GET)
	@ResponseBody
	public final PersonDetailedDO getPersonById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Person p = service.getPerson(id);
			if (p == null)
				throw new ResourceNotFoundException();
			return p.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

}