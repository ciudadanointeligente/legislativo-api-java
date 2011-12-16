package cl.votainteligente.legislativo.service.agrupation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Service;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PartyDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class PartyServiceImpl extends EntityManagerService implements
		PartyService {

	@Override
	public Party newParty(Party party) throws ServiceException {
		getEntityManager().persist(party);
		return party;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PartyDO> getAllParties(int pageNumber, int resultsPerPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Party p");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<PartyDO> listDO = new ArrayList<PartyDO>();
		for (Party party : (List<Party>) query.getResultList()) {
			listDO.add(party.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Party p");
		Long totalParties = (Long) queryCount.getSingleResult();

		return new Page<PartyDO>(listDO, pageNumber, resultsPerPage,
				totalParties);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PartyDO> findPartiesByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Party p where upper(p.firstName) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<PartyDO> listDO = new ArrayList<PartyDO>();
		for (Party party : (List<Party>) query.getResultList()) {
			listDO.add(party.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(p) from Party p where upper(p.firstName) like upper(?)");
		queryCount.setParameter(1, "%" + name + "%");
		Long totalParties = (Long) queryCount.getSingleResult();

		return new Page<PartyDO>(listDO, pageNumber, resultsPerPage,
				totalParties);
	}

	@Override
	public Party getParty(Long id) throws ServiceException {
		return getEntityManager().find(Party.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonDO> getCurrentAffiliatesByParty(Party p, int pageNumber,
			int resultsPerPage) {
		Query query = getEntityManager()
				.createQuery(
						"select distinct pa.person from PartyAffiliation pa where pa.party = ? and (pa.departureDate is null or departureDate >= ?)");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		query.setParameter(1, p);
		query.setParameter(2, new Date(), TemporalType.DATE);
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(distinct pa.person) from PartyAffiliation pa where pa.party = ? and (pa.departureDate is null or departureDate >= ?)");
		queryCount.setParameter(1, p);
		queryCount.setParameter(2, new Date(), TemporalType.DATE);
		Long totalParties = (Long) queryCount.getSingleResult();
		
		return new Page<PersonDO>(listDO, pageNumber, resultsPerPage,
				totalParties);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonDO> getHistoricalAffiliatesByParty(Party p,
			int pageNumber, int resultsPerPage) {
		Query query = getEntityManager()
				.createQuery(
						"select distinct pa.person from PartyAffiliation pa where pa.party = ?");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		query.setParameter(1, p);
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(distinct pa.person) from PartyAffiliation pa where pa.party = ?");
		queryCount.setParameter(1, p);
		Long totalParties = (Long) queryCount.getSingleResult();
		
		return new Page<PersonDO>(listDO, pageNumber, resultsPerPage,
				totalParties);
	}
}
