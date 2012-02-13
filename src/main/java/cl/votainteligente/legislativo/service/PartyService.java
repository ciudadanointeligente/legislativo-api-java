package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.DO.PartyDO;
import cl.votainteligente.legislativo.model.DO.PartyDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;

public interface PartyService {
	Party newParty(Party party) throws ServiceException;

	Page<PartyDO> getAllParties(int pageNumber, int resultsPerPage)
			throws ServiceException;

	Page<PartyDO> findPartiesByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException;

	Party getParty(Long id) throws ServiceException;

	PartyDetailedDO getPartyDetailedDO(Long id) throws ServiceException;

	Page<PersonDO> getCurrentAffiliatesByParty(Party p, int pageNumber,
			int resultsPerPage);

	Page<PersonDO> getHistoricalAffiliatesByParty(Party p, int pageNumber,
			int resultsPerPage);
}
