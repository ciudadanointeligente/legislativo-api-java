package cl.votainteligente.legislativo.service.agrupation;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Agrupation;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.AgrupationDO;
import cl.votainteligente.legislativo.model.domainobjects.AgrupationDetailedDO;

public interface AgrupationService {
	Agrupation newAgrupation(Agrupation agrupation) throws ServiceException;

	Page<AgrupationDO> getAllAgrupation(int pageNumber, int resultsPerPage)
			throws ServiceException;

	Page<AgrupationDO> findAgrupationByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException;

	Agrupation getAgrupationById(Long id) throws ServiceException;

	public Page<AgrupationDO> getAgrupationsByPerson(Person person,
			int pageNumber, int resultsPerPage);

	AgrupationDetailedDO getAgrupationDetailedDO(Long id)
			throws ServiceException;

}
