package cl.votainteligente.legislativo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.model.DO.CommissionDO;
import cl.votainteligente.legislativo.model.DO.CommissionDetailedDO;
import cl.votainteligente.legislativo.service.CommissionService;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class CommissionServiceImpl extends EntityManagerService implements
		CommissionService {

	@Override
	public Commission newCommission(Commission commission)
			throws ServiceException {
		getEntityManager().persist(commission);
		return commission;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CommissionDO> getAllCommissions(int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select c from Commission c");
		long totalCommissions = query.getResultList().size();
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<CommissionDO> listDO = new ArrayList<CommissionDO>();
		for (Commission commission : (List<Commission>) query.getResultList()) {
			listDO.add((CommissionDO)commission.asDomainObject());
		}
		return new Page<CommissionDO>(listDO, pageNumber, resultsPerPage,
				totalCommissions);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CommissionDO> findCommissionsByName(String name,
			int pageNumber, int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select c from Commission c where upper(c.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		long totalCommissions = query.getResultList().size();
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<CommissionDO> listDO = new ArrayList<CommissionDO>();
		for (Commission commission : (List<Commission>) query.getResultList()) {
			listDO.add((CommissionDO)commission.asDomainObject());
		}
		return new Page<CommissionDO>(listDO, pageNumber, resultsPerPage,
				totalCommissions);
	}

	@Override
	public Commission getCommissionById(Long id) throws ServiceException {
		return getEntityManager().find(Commission.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CommissionDO> getCommissionsByType(
			CommissionType commissionType, int pageNumber, int resultsPerPage) {
		Query query = getEntityManager().createQuery(
				"select c from Commission c where c.commissionType = ?");
		query.setParameter(1, commissionType);
		long totalCommissions = query.getResultList().size();
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<CommissionDO> listDO = new ArrayList<CommissionDO>();
		for (Commission commission : (List<Commission>) query.getResultList()) {
			listDO.add((CommissionDO)commission.asDomainObject());
		}
		return new Page<CommissionDO>(listDO, pageNumber, resultsPerPage,
				totalCommissions);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CommissionDO> getCommissionsByChamber(Chamber chamber,
			int pageNumber, int resultsPerPage) {
		Query query = getEntityManager().createQuery(
				"select c from Commission c where c.chamber = ?");
		query.setParameter(1, chamber);
		long totalCommissions = query.getResultList().size();
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<CommissionDO> listDO = new ArrayList<CommissionDO>();
		for (Commission commission : (List<Commission>) query.getResultList()) {
			listDO.add((CommissionDO)commission.asDomainObject());
		}
		return new Page<CommissionDO>(listDO, pageNumber, resultsPerPage,
				totalCommissions);
	}

	@Override
	public CommissionDetailedDO getCommissionDetailedDO(Long id)
			throws ServiceException {
		return new CommissionDetailedDO(getCommissionById(id));
	}

	@Override
	public CommissionDO getCommissionDO(Long id) throws ServiceException {
		return (CommissionDO)getCommissionById(id).asDomainObject();
	}

}
