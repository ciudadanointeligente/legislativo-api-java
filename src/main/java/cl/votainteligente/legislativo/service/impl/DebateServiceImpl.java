package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.DO.DebateDO;
import cl.votainteligente.legislativo.model.DO.DebateDetailedDO;
import cl.votainteligente.legislativo.service.DebateService;
import cl.votainteligente.legislativo.service.EntityManagerService;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

@Service
public class DebateServiceImpl extends EntityManagerService implements DebateService {

	@Override
	public Debate saveDebate(Debate debate) throws ServiceException {
		if (debate.getId() != null) {
			getEntityManager().merge(debate);
		} else {
			getEntityManager().persist(debate);
		}
		return debate;
	}

	@Override
	public Debate getDebate(long id) throws ServiceException {
		return getEntityManager().find(Debate.class, id);
	}

	public DebateDetailedDO getDebateDetailedDO(long id) throws ServiceException {
		Debate debate = getDebate(id);

		if (debate == null) {
			return null;
		}
		return debate.asDetailedDomainObject();
	}

	@Override
	public Page<DebateDO> getDebateByBill(Bill bill, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("Select p from Debate p where p.bill = ?");
		query.setParameter(1, bill);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Debate> debateList = query.getResultList();
		List<DebateDO> debateDOList = new LinkedList<DebateDO>();

		for (Debate debate : debateList) {
			debateDOList.add(debate.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("Select count(p) from Debate p where p.bill = ?");
		queryCount.setParameter(1, bill);
		long total = (Long) queryCount.getSingleResult();
		return new Page<DebateDO>(debateDOList, page, perPage, total);
	}

	@Override
	public Page<DebateDO> getDebateByDateRange(Date from, Date to, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("Select p from Debate p where p.date <= ? and p.date >= ?");
		query.setParameter(1, to, TemporalType.DATE);
		query.setParameter(2, from, TemporalType.DATE);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Debate> debateList = query.getResultList();
		List<DebateDO> debateDOList = new LinkedList<DebateDO>();

		for (Debate debate : debateList) {
			debateDOList.add(debate.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("Select count(p) from Debate p where p.date <= ? and p.date >= ?");
		queryCount.setParameter(1, to);
		queryCount.setParameter(2, from);
		long total = (Long) queryCount.getSingleResult();
		return new Page<DebateDO>(debateDOList, page, perPage, total);
	}
}
