package cl.votainteligente.legislativo.service.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Session;
import cl.votainteligente.legislativo.model.domainobjects.SessionDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class SessionServiceImpl extends EntityManagerService implements
		SessionService {

	@SuppressWarnings("unchecked")
	@Override
	public Page<SessionDO> getAllSessionDO(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select s from Session s");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionDO> listDO = new ArrayList<SessionDO>();
		for (Session session : (List<Session>) query
				.getResultList()) {
			listDO.add(session.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(s) from Session s");
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SessionDO>(listDO, page, perPage, total);
	}

	@Override
	public SessionDetailedDO getSessionDetailedDO(Long id)
			throws ServiceException {
		return getSession(id).asDetailedDomainObject();
	}

	@Override
	public Session getSession(Long id) throws ServiceException {
		return getEntityManager().find(Session.class, id);
	}

	@SuppressWarnings("unchecked")
	public Page<SessionDO> getByDateRange(Date from, Date to, int page,
			int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select s from Session s where s.date between ? and ?");
		query.setParameter(1, from, TemporalType.DATE);
		query.setParameter(2, to, TemporalType.DATE);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionDO> listDO = new ArrayList<SessionDO>();
		for (Session session : (List<Session>) query
				.getResultList()) {
			listDO.add(session.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(s) from Session s where s.date between ? and ?");
		queryCount.setParameter(1, from, TemporalType.DATE);
		queryCount.setParameter(2, to, TemporalType.DATE);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SessionDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<SessionDO> getByLegislature(Long id, int page,
			int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select s from Session s where s.legislature = ?");
		query.setParameter(1, id);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionDO> listDO = new ArrayList<SessionDO>();
		for (Session session : (List<Session>) query
				.getResultList()) {
			listDO.add(session.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(s) from Session s where s.legislature = ?");
		queryCount.setParameter(1, id);
		Long total = (Long) queryCount.getSingleResult();
		return new Page<SessionDO>(listDO, page, perPage, total);
	}
}
