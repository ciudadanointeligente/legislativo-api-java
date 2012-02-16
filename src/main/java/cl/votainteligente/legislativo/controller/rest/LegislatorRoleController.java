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

@Path("legislatorRole")
@Controller
public class LegislatorRoleController implements LegislatorRoleAPI {

	@Autowired
	LegislatorRoleService legislatorRoleService;
	@Autowired
	PersonService personService;
	@Autowired
	DistrictService districtService;
	@Autowired
	CircunscriptionService circunscriptionService;

	@RequestMapping(params = { "id" }, value = "legislatorRole/any", method = RequestMethod.GET)
	@ResponseBody
	public final LegislatorDetailedDO getLegislatorById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			LegislatorDetailedDO legislatorDetail = legislatorRoleService.getLegislatorDetailedDO(id);

			if (legislatorDetail == null) {
				throw new ResourceNotFoundException();
			}

			return legislatorDetail;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislatorRole/district", method = RequestMethod.GET)
	@ResponseBody
	public final Page<LegislatorDO> getLegislatorByDistrict(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			District district = districtService.getDistrict(id);

			if(district == null) {
				throw new ResourceNotFoundException();
			}

			return legislatorRoleService.getLegislatorsByDistrict(district, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "legislatorRole/circunscription", method = RequestMethod.GET)
	@ResponseBody
	public final Page<LegislatorDO> getLegislatorByCircunscription(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Circunscription circunscription = circunscriptionService.getCircunscription(id);

			if(circunscription == null) {
				throw new ResourceNotFoundException();
			}

			return legislatorRoleService.getLegislatorsByCircunscription(circunscription, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislatorRole/person", method = RequestMethod.GET)
	@ResponseBody
	public final Page<LegislatorDO> getLegislatorsByPerson(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Person person = personService.getPerson(id);

			if (person == null) {
				throw new ResourceNotFoundException();
			}

			return legislatorRoleService.getLegislatorsByPerson(person, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislatorRole/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getAllLegislators(
			@RequestParam(value = "page", required = false, defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE) final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE) final int perPage) {
		try {
			return legislatorRoleService.getPersonDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "legislatorRole/current", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getCurrentLegislators(
			@RequestParam(value = "page", required = false, defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE) final int page,
			@RequestParam(value = "perPage", required = false, defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE) final int perPage) {
		try {
			return legislatorRoleService.getLegislatorPersonDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}