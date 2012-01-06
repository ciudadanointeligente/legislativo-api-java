package cl.votainteligente.legislativo.service.agrupation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Agrupation;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.AgrupationDO;
import cl.votainteligente.legislativo.model.domainobjects.AgrupationDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class AgrupationServiceImpl extends EntityManagerService implements
		AgrupationService {

	@Override
	public Agrupation newAgrupation(Agrupation agrupation)
			throws ServiceException {
		getEntityManager().persist(agrupation);
		return agrupation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AgrupationDO> getAllAgrupation(int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Agrupation p");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<AgrupationDO> listDO = new ArrayList<AgrupationDO>();
		for (Agrupation agrupation : (List<Agrupation>) query.getResultList()) {
			listDO.add(agrupation.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Agrupation p");
		Long totalAgrupations = (Long) queryCount.getSingleResult();

		return new Page<AgrupationDO>(listDO, pageNumber, resultsPerPage,
				totalAgrupations);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AgrupationDO> findAgrupationByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Agrupation p where upper(p.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<AgrupationDO> listDO = new ArrayList<AgrupationDO>();
		for (Agrupation agrupation : (List<Agrupation>) query.getResultList()) {
			listDO.add(agrupation.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(p) from Party p where upper(p.name) like upper(?)");
		queryCount.setParameter(1, "%" + name + "%");
		Long totalAgrupations = (Long) queryCount.getSingleResult();

		return new Page<AgrupationDO>(listDO, pageNumber, resultsPerPage,
				totalAgrupations);
	}

	@Override
	public Agrupation getAgrupationById(Long id) throws ServiceException {
		return getEntityManager().find(Agrupation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<AgrupationDO> getAgrupationsByPerson(Person person,
			int pageNumber, int resultsPerPage) {
		Query query = getEntityManager()
				.createQuery(
						"select p.agrupation from AgrupationAffiliation p where p.person = ?");
		query.setParameter(1, person);
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<AgrupationDO> listDO = new ArrayList<AgrupationDO>();
		for (Agrupation agrupation : (List<Agrupation>) query.getResultList()) {
			listDO.add(agrupation.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(p.agrupation) from AgrupationAffiliation p where p.person = ?");
		queryCount.setParameter(1, person);
		Long totalAgrupations = (Long) queryCount.getSingleResult();

		return new Page<AgrupationDO>(listDO, pageNumber, resultsPerPage,
				totalAgrupations);
	}

	@Override
	public AgrupationDetailedDO getAgrupationDetailedDO(Long id)
			throws ServiceException {
		Agrupation tmp = this.getAgrupationById(id);
		return tmp.asDetailedDomainObject();
	}

}
