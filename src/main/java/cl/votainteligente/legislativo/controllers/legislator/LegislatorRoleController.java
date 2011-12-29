package cl.votainteligente.legislativo.controllers.legislator;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDO;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonPartyDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.geo.CircunscriptionService;
import cl.votainteligente.legislativo.service.geo.DistrictService;
import cl.votainteligente.legislativo.service.legislator.LegislatorRoleService;
import cl.votainteligente.legislativo.service.person.PersonService;

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
			return service.getLegislatorDetailedDO(id);
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
	public final Page<PersonPartyDO> getLegislatorPersons(
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