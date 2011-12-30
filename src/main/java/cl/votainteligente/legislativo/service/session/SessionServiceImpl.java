package cl.votainteligente.legislativo.service.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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
		Query query = getEntityManager().createQuery("select s from Session s");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SessionDO> listDO = new ArrayList<SessionDO>();
		for (Session session : (List<Session>) query.getResultList()) {
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
	public Session getSession(Long id)
			throws ServiceException {
		return getEntityManager().find(Session.class, id);
	}
}
