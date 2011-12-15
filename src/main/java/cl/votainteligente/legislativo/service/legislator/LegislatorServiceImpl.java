package cl.votainteligente.legislativo.service.legislator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Service;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.PartyAffiliation;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorPersonDO;
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
		Query count = getEntityManager().createQuery(
				"select p from Legislator p where p.district = ?");
		count.setParameter(1, district);
		long total = (Long) count.getSingleResult();
		return new Page<Legislator>(results, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonDO> getKPersonDOs(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select pa from PartyAffiliation pa join pa.person.roles r where r.class=Legilsator");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> results = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList())
			results.add(person.asDomainObject());
		Query count = getEntityManager()
				.createQuery(
						"select count(pa) from PartyAffiliation pa join pa.person.roles r where r.class=Legilsator");
		long totalElements = (Long) count.getSingleResult();
		return new Page<PersonDO>(results, page, perPage, totalElements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<LegislatorPersonDO> getLegislatorPersonDOs(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select pa from PartyAffiliation pa join pa.person.roles r where r.class=Legislator and (r.endDate >= ? or r.endDate is null)");
		query.setParameter(1, new Date(), TemporalType.DATE);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		ArrayList<LegislatorPersonDO> listDO = new ArrayList<LegislatorPersonDO>();
		for (PartyAffiliation affiliation : (List<PartyAffiliation>) query
				.getResultList()) {
			listDO.add(new LegislatorPersonDO(affiliation.getPerson(),
					affiliation.getParty()));
		}
		Query count = getEntityManager()
				.createQuery(
						"select count(pa) from PartyAffiliation pa join pa.person.roles r where r.class=Legislator and (r.endDate >= ? or r.endDate is null)");
		count.setParameter(1, new Date(), TemporalType.DATE);
		Long totalElements = (Long) count.getSingleResult();
		return new Page<LegislatorPersonDO>(listDO, page, perPage,
				totalElements);
	}
}