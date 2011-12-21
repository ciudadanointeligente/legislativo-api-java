package cl.votainteligente.legislativo.service.legislator;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDO;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonPartyDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

public interface LegislatorService {
	Legislator newLegislator(Legislator legislator) throws ServiceException;

	Legislator getLegislator(Long id) throws ServiceException;

	LegislatorDetailedDO getLegislatorDetailedDO(Long id)
			throws ServiceException;

	Page<LegislatorDO> getLegislatorsByPerson(Person person, int page,
			int perPage) throws ServiceException;

	Page<LegislatorDO> getLegislatorsByCircunscription(
			Circunscription circunscription, int page, int perPage)
			throws ServiceException;

	Page<LegislatorDO> getLegislatorsByDistrict(District district, int page,
			int perPage) throws ServiceException;

	Page<PersonDO> getPersonDOs(int page, int perPage) throws ServiceException;

	Page<PersonPartyDO> getLegislatorPersonDOs(int page, int perPage)
			throws ServiceException;
}
