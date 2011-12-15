package cl.votainteligente.legislativo.service.agrupation;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.domainobjects.PartyDO;

public interface PartyService {
	Party newParty(Party party) throws ServiceException;

	List<PartyDO> getAllParties() throws ServiceException;

	List<PartyDO> findPartiesByName(String name) throws ServiceException;

	Party getParty(Long id) throws ServiceException;
}
