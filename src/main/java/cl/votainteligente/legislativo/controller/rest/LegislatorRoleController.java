package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.LegislatorRoleAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.DO.LegislatorDO;
import cl.votainteligente.legislativo.model.DO.LegislatorDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("legislator_role")
@Controller
public class LegislatorRoleController implements LegislatorRoleAPI {

	@Autowired
	LegislatorRoleService service;

	@Autowired
	PersonService person;

	@Autowired
	DistrictService district;

	@Autowired
	CircunscriptionService circunscription;

	@RequestMapping(params = { "id" }, value = "legislator_role/any", method = RequestMethod.GET)
	@ResponseBody
	public final LegislatorDetailedDO getLegislatorById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			LegislatorDetailedDO leg = service.getLegislatorDetailedDO(id);
			if (leg == null)
				throw new ResourceNotFoundException();
			return leg;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislator_role/district", method = RequestMethod.GET)
	@ResponseBody
	public final Page<LegislatorDO> getLegislatorByDistrict(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") final int perPage) {
		try {
			District tmp = district.getDistrict(id);
			if(tmp == null)
				throw new ResourceNotFoundException();
			return service.getLegislatorsByDistrict(tmp, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislator_role/circunscription", method = RequestMethod.GET)
	@ResponseBody
	public final Page<LegislatorDO> getLegislatorByCircunscription(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") final int perPage) {
		try {
			Circunscription tmp = circunscription.getCircunscription(id);
			if(tmp == null)
				throw new ResourceNotFoundException();
			return service.getLegislatorsByCircunscription(tmp, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator_role/person", method = RequestMethod.GET)
	@ResponseBody
	public final Page<LegislatorDO> getLegislatorsByPerson(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") final int perPage) {
		try {
			Person legislator = person.getPerson(id);
			if (legislator == null)
				throw new ResourceNotFoundException();
			return service.getLegislatorsByPerson(legislator, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator_role/people", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getKLegislatorPersons(
			@RequestParam(value = "page", required = false, defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE) final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE) final int perPage) {
		try {
			return service.getPersonDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator_role/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getLegislatorPersons(
			@RequestParam(value = "page", required = false, defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE) final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE) final int perPage) {
		try {
			return service.getLegislatorPersonDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}