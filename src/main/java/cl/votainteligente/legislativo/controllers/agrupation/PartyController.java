package cl.votainteligente.legislativo.controllers.agrupation;

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
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.domainobjects.PartyDO;
import cl.votainteligente.legislativo.model.domainobjects.PartyDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.agrupation.PartyService;

@Path("party")
@Controller
public class PartyController implements PartyAPI {

	@Autowired
	PartyService partyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.votainteligente.legislativo.controllers.agrupation.PartyAPI#getAll
	 * (int, int)
	 */
	@RequestMapping(value = "party/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PartyDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return partyService.getAllParties(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.votainteligente.legislativo.controllers.agrupation.PartyAPI#getAll
	 * (java.lang.String, int, int)
	 */
	@RequestMapping(params = { "name" }, value = "party/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PartyDO> getAll(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return partyService.findPartiesByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cl.votainteligente.legislativo.controllers.agrupation.PartyAPI#getById
	 * (long)
	 */
	@RequestMapping(value = "party/any", method = RequestMethod.GET)
	@ResponseBody
	public final PartyDetailedDO getById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Party p = partyService.getParty(id);
			if(p == null)
				throw new ResourceNotFoundException();
			return p.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.votainteligente.legislativo.controllers.agrupation.PartyAPI#
	 * getHistoricalAffiliatesByParty(long, int, int)
	 */
	@RequestMapping(value = "party/historicalAffiliates", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getHistoricalAffiliatesByParty(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Party party = partyService.getParty(id);
			if(party==null)
				throw new ResourceNotFoundException();
			return partyService.getHistoricalAffiliatesByParty(party, page,
					perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cl.votainteligente.legislativo.controllers.agrupation.PartyAPI#
	 * getCurrentAffiliatesByParty(long, int, int)
	 */
	@RequestMapping(value = "party/currentAffiliates", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getCurrentAffiliatesByParty(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Party party = partyService.getParty(id);
			if(party==null)
				throw new ResourceNotFoundException();
			return partyService.getCurrentAffiliatesByParty(party, page,
					perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
