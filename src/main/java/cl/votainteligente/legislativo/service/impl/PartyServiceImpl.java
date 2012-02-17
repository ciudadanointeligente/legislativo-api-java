package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.DO.PartyDO;
import cl.votainteligente.legislativo.model.DO.PartyDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.PartyService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

@Service
public class PartyServiceImpl extends EntityManagerService implements PartyService {

	@Override
	public Party newParty(Party party) throws ServiceException {
		getEntityManager().persist(party);
		return party;
	}

	@Override
	public Page<PartyDO> getAllParties(int pageNumber, int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Party p");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<PartyDO> partyDOList = new ArrayList<PartyDO>();

		for (Party party : (List<Party>) query.getResultList()) {
			partyDOList.add((PartyDO) party.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(p) from Party p");
		Long totalParties = (Long) queryCount.getSingleResult();

		return new Page<PartyDO>(partyDOList, pageNumber, resultsPerPage, totalParties);
	}

	@Override
	public Page<PartyDO> findPartiesByName(String name, int pageNumber, int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Party p where upper(p.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<PartyDO> partyDOList = new ArrayList<PartyDO>();

		for (Party party : (List<Party>) query.getResultList()) {
			partyDOList.add((PartyDO) party.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(p) from Party p where upper(p.name) like upper(?)");
		queryCount.setParameter(1, "%" + name + "%");
		Long totalParties = (Long) queryCount.getSingleResult();

		return new Page<PartyDO>(partyDOList, pageNumber, resultsPerPage, totalParties);
	}

	@Override
	public Party getParty(Long id) throws ServiceException {
		return getEntityManager().find(Party.class, id);
	}

	@Override
	public PartyDetailedDO getPartyDetailedDO(Long id) throws ServiceException {
		return new PartyDetailedDO(getParty(id));
	}

	@Override
	public Page<PersonDO> getCurrentAffiliatesByParty(Party party, int pageNumber, int resultsPerPage) {
		Query query = getEntityManager().createQuery("select distinct pa.person from AgrupationAffiliation pa where pa.agrupation = ? and (pa.departureDate is null or pa.departureDate >= ?)");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		query.setParameter(1, party);
		query.setParameter(2, new Date(), TemporalType.DATE);
		List<PersonDO> personDOList = new ArrayList<PersonDO>();

		for (Person person : (List<Person>) query.getResultList()) {
			personDOList.add(person.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(distinct pa.person) from AgrupationAffiliation pa where pa.agrupation = ? and (pa.departureDate is null or pa.departureDate >= ?)");
		queryCount.setParameter(1, party);
		queryCount.setParameter(2, new Date(), TemporalType.DATE);
		Long totalParties = (Long) queryCount.getSingleResult();

		return new Page<PersonDO>(personDOList, pageNumber, resultsPerPage, totalParties);
	}

	@Override
	public Page<PersonDO> getHistoricalAffiliatesByParty(Party party, int pageNumber, int resultsPerPage) {
		Query query = getEntityManager().createQuery("select distinct pa.person from AgrupationAffiliation pa where pa.agrupation = ?");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		query.setParameter(1, party);
		List<PersonDO> personDO = new ArrayList<PersonDO>();

		for (Person person : (List<Person>) query.getResultList()) {
			personDO.add(person.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(distinct pa.person) from AgrupationAffiliation pa where pa.agrupation = ?");
		queryCount.setParameter(1, party);
		Long totalParties = (Long) queryCount.getSingleResult();

		return new Page<PersonDO>(personDO, pageNumber, resultsPerPage, totalParties);
	}
}
