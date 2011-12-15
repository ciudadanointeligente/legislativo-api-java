package cl.votainteligente.legislativo.service.legislator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;
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
	public Page<Legislator> getLegislatorsByPerson(Person person, int page,
			int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select l from Legislator l where l.person = ?");
		query.setParameter(1, person);
		List<Legislator> results = query.getResultList();
		return Page.listToPage(results, page, perPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Legislator> getLegislatorsByCircunscription(
			Circunscription circunscription, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Legislator p where p.circunscription = ?");
		query.setParameter(1, circunscription);
		List<Legislator> results = query.getResultList();
		return Page.listToPage(results, page, perPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Legislator> getLegislatorsByDistrict(District district,
			int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Legislator p where p.district = ?");
		query.setParameter(1, district);
		List<Legislator> results = query.getResultList();
		return Page.listToPage(results, page, perPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonDO> getPersonDOsList() throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select distinct l.person from Legislator l");
		List<PersonDO> results = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList())
			results.add(person.asDomainObject());
		return results;
	}

	@Override
	public Page<PersonDO> getPersonDOs() throws ServiceException {
		List<PersonDO> results = getPersonDOsList();
		return Page.listToPage(results, 1, results.size());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonDO> getKPersonDOs(int pageNumber, int resultsPerPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select distinct l.person from Legislator l");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<PersonDO> results = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList())
			results.add(person.asDomainObject());
		Query queryCount = getEntityManager().createQuery(
				"select count(distinct l.person) from Legislator l");
		Long totalPeople = (Long) queryCount.getSingleResult();
		return new Page<PersonDO>(results, pageNumber, resultsPerPage,
				totalPeople);

	}

}
