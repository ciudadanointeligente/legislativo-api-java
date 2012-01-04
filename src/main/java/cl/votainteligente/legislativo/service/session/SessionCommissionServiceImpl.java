package cl.votainteligente.legislativo.service.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.SessionCommission;
import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class SessionCommissionServiceImpl extends EntityManagerService
		implements SessionCommissionService {

	@SuppressWarnings("unchecked")
	@Override
	public Page<SessionCommissionDO> getAllSessionCommissionDO(int page,
			int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select s from SessionCommission s");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionCommissionDO> listDO = new ArrayList<SessionCommissionDO>();
		for (SessionCommission session : (List<SessionCommission>) query
				.getResultList()) {
			listDO.add(session.asSessionCommissionDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(s) from SessionCommission s");
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SessionCommissionDO>(listDO, page, perPage, total);
	}

	@Override
	public SessionCommissionDetailedDO getSessionCommissionDetailedDO(Long id)
			throws ServiceException {
		return getSessionCommission(id).asSessionCommissionDetailedDomainObject();
	}

	@Override
	public SessionCommission getSessionCommission(Long id)
			throws ServiceException {
		return getEntityManager().find(SessionCommission.class, id);
	}

	@SuppressWarnings("unchecked")
	public Page<SessionCommissionDO> getByDateRange(Date from, Date to,
			int page, int perPage) throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select s from SessionCommission s where s.date between ? and ?");
		query.setParameter(1, from, TemporalType.DATE);
		query.setParameter(2, to, TemporalType.DATE);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionCommissionDO> listDO = new ArrayList<SessionCommissionDO>();
		for (SessionCommission session : (List<SessionCommission>) query
				.getResultList()) {
			listDO.add(session.asSessionCommissionDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(s) from SessionCommission s where s.date between ? and ?");
		queryCount.setParameter(1, from, TemporalType.DATE);
		queryCount.setParameter(2, to, TemporalType.DATE);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SessionCommissionDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<SessionCommissionDO> getByLegislature(Long id, int page,
			int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select s from SessionCommission s where s.legislature = ?");
		query.setParameter(1, id);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionCommissionDO> listDO = new ArrayList<SessionCommissionDO>();
		for (SessionCommission session : (List<SessionCommission>) query
				.getResultList()) {
			listDO.add(session.asSessionCommissionDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(s) from SessionCommission s where s.legislature = ?");
		queryCount.setParameter(1, id);
		Long total = (Long) queryCount.getSingleResult();
		return new Page<SessionCommissionDO>(listDO, page, perPage, total);
	}
}
