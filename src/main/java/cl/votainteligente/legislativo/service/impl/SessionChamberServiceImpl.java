package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.SessionChamber;
import cl.votainteligente.legislativo.model.DO.SessionChamberDO;
import cl.votainteligente.legislativo.model.DO.SessionChamberDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.SessionChamberService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

@Service
public class SessionChamberServiceImpl extends EntityManagerService implements SessionChamberService {

	@Override
	public Page<SessionChamberDO> getAllSessionChamberDO(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select s from SessionChamber s");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionChamberDO> sessionChamberDOList = new ArrayList<SessionChamberDO>();

		for (SessionChamber session : (List<SessionChamber>) query.getResultList()) {
			sessionChamberDOList.add(session.asSessionChamberDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(s) from SessionChamber s");
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SessionChamberDO>(sessionChamberDOList, page, perPage, total);
	}

	@Override
	public SessionChamberDetailedDO getSessionChamberDetailedDO(Long id) throws ServiceException {
		return getSessionChamber(id).asSessionChamberDetailedDomainObject();
	}

	@Override
	public SessionChamber getSessionChamber(Long id) throws ServiceException {
		return getEntityManager().find(SessionChamber.class, id);
	}

	public Page<SessionChamberDO> getByDateRange(Date from, Date to, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select s from SessionChamber s where s.date between ? and ?");
		query.setParameter(1, from, TemporalType.DATE);
		query.setParameter(2, to, TemporalType.DATE);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionChamberDO> sessionChamberDOList = new ArrayList<SessionChamberDO>();

		for (SessionChamber session : (List<SessionChamber>) query.getResultList()) {
			sessionChamberDOList.add(session.asSessionChamberDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(s) from SessionChamber s where s.date between ? and ?");
		queryCount.setParameter(1, from, TemporalType.DATE);
		queryCount.setParameter(2, to, TemporalType.DATE);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SessionChamberDO>(sessionChamberDOList, page, perPage, total);
	}

	@Override
	public Page<SessionChamberDO> getByLegislature(Long id, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select s from SessionChamber s where s.legislature = ?");
		query.setParameter(1, id);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionChamberDO> sessionChamberDOList = new ArrayList<SessionChamberDO>();

		for (SessionChamber session : (List<SessionChamber>) query.getResultList()) {
			sessionChamberDOList.add(session.asSessionChamberDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(s) from SessionChamber s where s.legislature = ?");
		queryCount.setParameter(1, id);
		Long total = (Long) queryCount.getSingleResult();
		return new Page<SessionChamberDO>(sessionChamberDOList, page, perPage, total);
	}
}
