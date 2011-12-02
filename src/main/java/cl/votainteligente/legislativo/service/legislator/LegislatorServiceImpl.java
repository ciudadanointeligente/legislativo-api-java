package cl.votainteligente.legislativo.service.legislator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class LegislatorServiceImpl extends EntityManagerService implements
		LegislatorService {

	@Override
	public Legislator getLegislator(Long id) throws ServiceException {
		return getEntityManager().find(Legislator.class, id);
	}

	@Override
	public Legislator newLegislator(Legislator legislator)
			throws ServiceException {
		getEntityManager().persist(legislator);
		return legislator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Legislator> getLegislatorsByPerson(Person person)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select l from Legislator l where Person = ?");
		query.setParameter(1, person);
		List<Legislator> results = query.getResultList();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonDO> getPersonDOs() throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select distinct l.person from Legislator l");
		List<PersonDO> list = new ArrayList<PersonDO>();
		for(Person person : (List<Person>)query.getResultList())
			list.add(person.asDomainObject());
		return list;
	}

}
