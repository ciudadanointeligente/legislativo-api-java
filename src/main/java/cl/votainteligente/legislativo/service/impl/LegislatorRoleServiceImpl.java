package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.*;
import cl.votainteligente.legislativo.model.DO.LegislatorDO;
import cl.votainteligente.legislativo.model.DO.LegislatorDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.LegislatorRoleService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

@Service
public class LegislatorRoleServiceImpl extends EntityManagerService implements LegislatorRoleService {

	@Override
	public LegislatorRole getLegislator(Long id) throws ServiceException {
		return getEntityManager().find(LegislatorRole.class, id);
	}

	@Override
	public LegislatorDetailedDO getLegislatorDetailedDO(Long id) throws ServiceException {
		LegislatorRole leg = getLegislator(id);

		if (leg == null) {
			return null;
		}

		return new LegislatorDetailedDO(leg);
	}

	@Override
	public LegislatorRole newLegislator(LegislatorRole legislator) throws ServiceException {
		getEntityManager().persist(legislator);
		return legislator;
	}

	@Override
	public Page<LegislatorDO> getLegislatorsByPerson(Person person, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select l from LegislatorRole l where l.person = ?");
		query.setParameter(1, person);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<LegislatorDO> legislatorDOList = new ArrayList<LegislatorDO>();

		for (LegislatorRole legislator : (List<LegislatorRole>) query.getResultList()) {
			legislatorDOList.add(legislator.asDomainObject());
		}

		query = getEntityManager().createQuery("select count(l) from LegislatorRole l where l.person = ?");
		query.setParameter(1, person);
		long total = (Long) query.getSingleResult();
		return new Page<LegislatorDO>(legislatorDOList, page, perPage, total);
	}

	@Override
	public Page<LegislatorDO> getLegislatorsByCircunscription(Circunscription circunscription, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from LegislatorRole p where p.circunscription = ?");
		query.setParameter(1, circunscription);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<LegislatorDO> legislatorDOList = new ArrayList<LegislatorDO>();
		for (LegislatorRole legislator : (List<LegislatorRole>) query.getResultList()) {
			legislatorDOList.add(legislator.asDomainObject());
		}
		query = getEntityManager().createQuery("select count(p) from LegislatorRole p where p.circunscription = ?");
		query.setParameter(1, circunscription);
		long total = (Long) query.getSingleResult();
		return new Page<LegislatorDO>(legislatorDOList, page, perPage, total);
	}

	@Override
	public Page<LegislatorDO> getLegislatorsByDistrict(District district, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from LegislatorRole p where p.district = ?");
		query.setParameter(1, district);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<LegislatorDO> legislatorDOList = new ArrayList<LegislatorDO>();

		for (LegislatorRole legislator : (List<LegislatorRole>) query.getResultList()) {
			legislatorDOList.add(legislator.asDomainObject());
		}

		Query count = getEntityManager().createQuery("select count(p) from LegislatorRole p where p.district = ?");
		count.setParameter(1, district);
		long total = (Long) count.getSingleResult();
		return new Page<LegislatorDO>(legislatorDOList, page, perPage, total);
	}

	@Override
	public Page<PersonDO> getPersonDOs(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select distinct p from Person p join p.roles r where r.class=LegislatorRole");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> personDOList = new ArrayList<PersonDO>();

		for (Person person : (List<Person>) query.getResultList()) {
			personDOList.add(person.asDomainObject());
		}

		Query count = getEntityManager().createQuery("select count(distinct p) from Person p join p.roles r where r.class=LegislatorRole");
		long totalElements = (Long) count.getSingleResult();
		return new Page<PersonDO>(personDOList, page, perPage, totalElements);
	}

	@Override
	public Page<PersonDO> getLegislatorPersonDOs(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select distinct p from Person p join p.roles r where r.class=LegislatorRole and r.startDate <= ? and (r.endDate is null or r.endDate >= ?)");
		Date currentDate = new Date();
		query.setParameter(1, currentDate, TemporalType.DATE);
		query.setParameter(2, currentDate, TemporalType.DATE);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> personDOList = new ArrayList<PersonDO>();

		for (Person person : (List<Person>) query.getResultList()) {
			personDOList.add(person.asDomainObject());
		}

		Query count = getEntityManager().createQuery("select count(distinct p) from Person p join p.roles r where r.class=LegislatorRole and r.startDate <= ? and (r.endDate is null or r.endDate >= ?)");
		count.setParameter(1, currentDate, TemporalType.DATE);
		count.setParameter(2, currentDate, TemporalType.DATE);
		Long totalElements = (Long) count.getSingleResult();
		return new Page<PersonDO>(personDOList, page, perPage, totalElements);
	}
}
