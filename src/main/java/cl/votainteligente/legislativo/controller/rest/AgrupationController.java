package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.AgrupationAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.Agrupation;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.DO.AgrupationDO;
import cl.votainteligente.legislativo.model.DO.AgrupationDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.service.AgrupationService;
import cl.votainteligente.legislativo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("agrupation")
@Controller
public class AgrupationController implements AgrupationAPI {

	@Autowired
	AgrupationService agrupationService;

	@Autowired
	PersonService personService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.AgrupationAPI#getAll(int, int)
	 */
	@RequestMapping(value = "agrupation/all", method = RequestMethod.GET)
	@ResponseBody
	public Page<AgrupationDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return agrupationService.getAllAgrupation(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.AgrupationAPI#getByName(java.lang.String, int, int)
	 */
	@RequestMapping(params = { "name" },value = "agrupation/any", method = RequestMethod.GET)
	@ResponseBody
	public Page<AgrupationDO> getByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return agrupationService.findAgrupationByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.AgrupationAPI#getByPerson (long, int, int)
	 */
	@RequestMapping(params = { "person" },value = "agrupation/any", method = RequestMethod.GET)
	@ResponseBody
	public Page<AgrupationDO> getByPerson(
			@RequestParam(value = "person", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Person tmp = personService.getPerson(id);
			if (tmp == null)
				throw new ResourceNotFoundException();
			return agrupationService.getAgrupationsByPerson(tmp, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.AgrupationAPI#getById(long)
	 */
	@RequestMapping(params = { "id" },value = "agrupation/any", method = RequestMethod.GET)
	@ResponseBody
	public AgrupationDetailedDO getById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Agrupation agrupation = agrupationService.getAgrupationById(id);
			if (agrupation == null)
				throw new ResourceNotFoundException();
			return agrupation.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.AgrupationAPI#getHistoricalAffiliatesByAgrupation (long, int, int)
	 */
	@RequestMapping(value = "agrupation/historicalAffiliates", method = RequestMethod.GET)
	@ResponseBody
	public Page<PersonDO> getHistoricalAffiliatesByAgrupation(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.AgrupationAPI#getCurrentAffiliatesByAgrupation (long, int, int)
	 */
	@RequestMapping(value = "agrupation/currentAffiliates", method = RequestMethod.GET)
	@ResponseBody
	public Page<PersonDO> getCurrentAffiliatesByAgrupation(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		// TODO Auto-generated method stub
		return null;
	}

}