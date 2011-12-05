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
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.service.geo.CircunscriptionService;
import cl.votainteligente.legislativo.service.geo.DistrictService;
import cl.votainteligente.legislativo.service.legislator.LegislatorService;
import cl.votainteligente.legislativo.service.person.PersonService;

import com.google.gson.Gson;

@Controller
public class LegislatorController {
	private Gson gson = new Gson();

	@Autowired
	LegislatorService service;

	@Autowired
	PersonService person;

	@Autowired
	DistrictService district;

	@Autowired
	CircunscriptionService circunscription;

	@RequestMapping(params = { "id" }, value = "legislator/period.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getLegislatorById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return gson.toJson(service.getLegislator(id));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislator/district.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getLegislatorByDistrict(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			District tmp = district.getDistrict(id);
			return gson.toJson(service.getLegislatorsByDistrict(tmp));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislator/circunscription.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getLegislatorByCircunscription(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Circunscription tmp = circunscription.getCircunscription(id);
			return gson.toJson(service.getLegislatorsByCircunscription(tmp));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator/person.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getLegislatorsByPerson(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Person legislator = person.getPerson(id);
			return gson.toJson(service.getLegislatorsByPerson(legislator));
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislator/people.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getLegislatorPersons() {
		try {
			return gson.toJson(service.getPersonDOs());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
