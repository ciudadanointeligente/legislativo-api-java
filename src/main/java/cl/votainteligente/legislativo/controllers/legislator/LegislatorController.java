package cl.votainteligente.legislativo.controllers.legislator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.Page;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.geo.CircunscriptionService;
import cl.votainteligente.legislativo.service.geo.DistrictService;
import cl.votainteligente.legislativo.service.legislator.LegislatorService;
import cl.votainteligente.legislativo.service.person.PersonService;

@Controller
public class LegislatorController {

	@Autowired
	LegislatorService service;

	@Autowired
	PersonService person;

	@Autowired
	DistrictService district;

	@Autowired
	CircunscriptionService circunscription;

	@RequestMapping(params = { "id" }, value = "legislator/period", method = RequestMethod.GET)
	@ResponseBody
	public final Legislator getLegislatorById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return service.getLegislator(id);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislator/district", method = RequestMethod.GET)
	@ResponseBody
	public final Page<Legislator> getLegislatorByDistrict(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") final int perPage) {
		try {
			District tmp = district.getDistrict(id);
			return service.getLegislatorsByDistrict(tmp,page,perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislator/circunscription", method = RequestMethod.GET)
	@ResponseBody
	public final Page<Legislator> getLegislatorByCircunscription(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") final int perPage) {
		try {
			Circunscription tmp = circunscription.getCircunscription(id);
			return service.getLegislatorsByCircunscription(tmp,page,perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator/person", method = RequestMethod.GET)
	@ResponseBody
	public final Page<Legislator> getLegislatorsByPerson(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") final int perPage) {
		try {
			Person legislator = person.getPerson(id);
			return service.getLegislatorsByPerson(legislator,page,perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator/allPeople", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getLegislatorPersons() {
		try {
			return service.getPersonDOs();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator/people", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getKLegislatorPersons(
			@RequestParam(value = "page", required = false, defaultValue = "1") final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") final int perPage) {
		try {
			return service.getKPersonDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
