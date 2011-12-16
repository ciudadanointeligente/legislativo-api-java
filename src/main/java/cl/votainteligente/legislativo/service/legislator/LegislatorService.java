package cl.votainteligente.legislativo.service.legislator;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonPartyDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

public interface LegislatorService {
	Legislator newLegislator(Legislator legislator) throws ServiceException;

	Legislator getLegislator(Long id) throws ServiceException;

	Page<Legislator> getLegislatorsByPerson(Person person, int page, int perPage)
			throws ServiceException;

	Page<Legislator> getLegislatorsByCircunscription(
			Circunscription circunscription, int page, int perPage)
			throws ServiceException;

	Page<Legislator> getLegislatorsByDistrict(District district, int page,
			int perPage) throws ServiceException;

	Page<PersonDO> getPersonDOs(int page, int perPage) throws ServiceException;

	Page<PersonPartyDO> getLegislatorPersonDOs(int page, int perPage)
			throws ServiceException;
}
