package cl.votainteligente.legislativo.service.legislator;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

public interface LegislatorService {
	Legislator newLegislator(Legislator legislator) throws ServiceException;

	Legislator getLegislator(Long id) throws ServiceException;

	List<Legislator> getLegislatorsByPerson(Person person)
			throws ServiceException;

	List<PersonDO> getPersonDOs() throws ServiceException;
}
