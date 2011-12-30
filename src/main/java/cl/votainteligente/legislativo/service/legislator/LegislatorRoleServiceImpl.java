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
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.AgrupationAffiliation;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDO;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonPartyDO;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class LegislatorRoleServiceImpl extends EntityManagerService implements
		LegislatorRoleService {

	@Override
	public LegislatorRole getLegislator(Long id) throws ServiceException {
		return getEntityManager().find(LegislatorRole.class, id);
	}

	@Override
	public LegislatorDetailedDO getLegislatorDetailedDO(Long id)
			throws ServiceException {
		LegislatorRole leg = getLegislator(id);
		if(leg==null)
			return null;
		return new LegislatorDetailedDO(leg);
	}

	@Override
	public LegislatorRole newLegislator(LegislatorRole legislator)
			throws ServiceException {
		getEntityManager().persist(legislator);
		return legislator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<LegislatorDO> getLegislatorsByPerson(Person person, int page,
			int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select l from LegislatorRole l where l.person = ?");
		query.setParameter(1, person);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<LegislatorDO> listDO = new ArrayList<LegislatorDO>();
		for (LegislatorRole legislator : (List<LegislatorRole>) query.getResultList()) {
			listDO.add(legislator.asDomainObject());
		}
		query = getEntityManager().createQuery(
				"select count(l) from LegislatorRole l where l.person = ?");
		query.setParameter(1, person);
		long total = (Long) query.getSingleResult();
		return new Page<LegislatorDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<LegislatorDO> getLegislatorsByCircunscription(
			Circunscription circunscription, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from LegislatorRole p where p.circunscription = ?");
		query.setParameter(1, circunscription);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<LegislatorDO> listDO = new ArrayList<LegislatorDO>();
		for (LegislatorRole legislator : (List<LegislatorRole>) query.getResultList()) {
			listDO.add(legislator.asDomainObject());
		}
		query = getEntityManager()
				.createQuery(
						"select count(p) from LegislatorRole p where p.circunscription = ?");
		query.setParameter(1, circunscription);
		long total = (Long) query.getSingleResult();
		return new Page<LegislatorDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<LegislatorDO> getLegislatorsByDistrict(District district,
			int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from LegislatorRole p where p.district = ?");
		query.setParameter(1, district);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<LegislatorDO> listDO = new ArrayList<LegislatorDO>();
		for (LegislatorRole legislator : (List<LegislatorRole>) query.getResultList()) {
			listDO.add(legislator.asDomainObject());
		}
		Query count = getEntityManager().createQuery(
				"select count(p) from LegislatorRole p where p.district = ?");
		count.setParameter(1, district);
		long total = (Long) count.getSingleResult();
		return new Page<LegislatorDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonDO> getPersonDOs(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select distinct p from Person p join p.roles r where r.class=LegislatorRole");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> results = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList())
			results.add(person.asDomainObject());
		Query count = getEntityManager()
				.createQuery(
						"select count(distinct p) from Person p join p.roles r where r.class=LegislatorRole");
		long totalElements = (Long) count.getSingleResult();
		return new Page<PersonDO>(results, page, perPage, totalElements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonPartyDO> getLegislatorPersonDOs(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select aa from AgrupationAffiliation aa join aa.person.roles r where r.class=LegislatorRole and (r.endDate >= ? "
								+ "or r.endDate is null) and aa.agrupation in (select p from Party p)");
		query.setParameter(1, new Date(), TemporalType.DATE);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		ArrayList<PersonPartyDO> listDO = new ArrayList<PersonPartyDO>();
		for (AgrupationAffiliation affiliation : (List<AgrupationAffiliation>) query
				.getResultList()) {
			listDO.add(new PersonPartyDO(affiliation.getPerson(),
					(Party) affiliation.getAgrupation()));
		}
		Query count = getEntityManager()
				.createQuery(
						"select count(aa) from AgrupationAffiliation aa join aa.person.roles r where r.class=LegislatorRole and (r.endDate >= ? "
								+ "or r.endDate is null) and aa.agrupation in (select p from Party p)");
		count.setParameter(1, new Date(), TemporalType.DATE);
		Long totalElements = (Long) count.getSingleResult();
		return new Page<PersonPartyDO>(listDO, page, perPage, totalElements);
	}

}