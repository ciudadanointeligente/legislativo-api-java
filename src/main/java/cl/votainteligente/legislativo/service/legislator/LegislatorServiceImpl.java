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
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Legislator> results = query.getResultList();
		query = getEntityManager().createQuery(
				"select count(l) from Legislator l where l.person = ?");
		query.setParameter(1, person);
		long total = (Long) query.getSingleResult();
		return new Page<Legislator>(results, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Legislator> getLegislatorsByCircunscription(
			Circunscription circunscription, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Legislator p where p.circunscription = ?");
		query.setParameter(1, circunscription);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Legislator> results = query.getResultList();
		query = getEntityManager()
				.createQuery(
						"select count(p) from Legislator p where p.circunscription = ?");
		query.setParameter(1, circunscription);
		long total = (Long) query.getSingleResult();
		return new Page<Legislator>(results, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Legislator> getLegislatorsByDistrict(District district,
			int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Legislator p where p.district = ?");
		query.setParameter(1, district);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Legislator> results = query.getResultList();
		query = getEntityManager().createQuery(
				"select p from Legislator p where p.district = ?");
		query.setParameter(1, district);
		long total = (Long) query.getSingleResult();
		return new Page<Legislator>(results, page, perPage, total);
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
