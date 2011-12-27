package cl.votainteligente.legislativo.service.debate;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.domainobjects.DebateDO;
import cl.votainteligente.legislativo.model.domainobjects.DebateDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class DebateServiceImpl extends EntityManagerService implements
		DebateService {

	@Override
	public Debate saveDebate(Debate d) throws ServiceException {
		if (d.getId() != null)
			getEntityManager().merge(d);
		else
			getEntityManager().persist(d);
		return d;
	}

	@Override
	public Debate getDebate(long id) throws ServiceException {
		return getEntityManager().find(Debate.class, id);
	}

	public DebateDetailedDO getDebateDetailedDO(long id)
			throws ServiceException {
		Debate debate = getDebate(id);
		if (debate == null)
			return null;
		return debate.asDetailedDomainObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<DebateDO> getDebateByBill(Bill bill, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"Select p from Debate p where p.bill = ?");
		query.setParameter(1, bill);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Debate> debates = query.getResultList();
		List<DebateDO> debatesDO = new LinkedList<DebateDO>();
		for (Debate d : debates)
			debatesDO.add(d.asDomainObject());
		Query queryCount = getEntityManager().createQuery(
				"Select count(p) from Debate p where p.bill = ?");
		queryCount.setParameter(1, bill);
		long total = (Long) queryCount.getSingleResult();
		return new Page<DebateDO>(debatesDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<DebateDO> getDebateByDateRange(Date from, Date to, int page,
			int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"Select p from Debate p where p.date <= ? and p.date >= ?");
		query.setParameter(1, to, TemporalType.DATE);
		query.setParameter(2, from, TemporalType.DATE);
		System.out.println("From: "+from.toString()+", to: "+to.toString());
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Debate> debates = query.getResultList();
		List<DebateDO> debatesDO = new LinkedList<DebateDO>();
		for (Debate d : debates)
			debatesDO.add(d.asDomainObject());
		Query queryCount = getEntityManager().createQuery(
				"Select count(p) from Debate p where p.date <= ? and p.date >= ?");
		queryCount.setParameter(1, to);
		queryCount.setParameter(2, from);
		long total = (Long) queryCount.getSingleResult();
		return new Page<DebateDO>(debatesDO, page, perPage, total);
	}
}
