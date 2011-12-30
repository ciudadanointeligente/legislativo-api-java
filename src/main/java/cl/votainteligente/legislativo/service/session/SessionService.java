package cl.votainteligente.legislativo.service.session;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Session;
import cl.votainteligente.legislativo.model.domainobjects.SessionDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionDetailedDO;

public interface SessionService {

	Page<SessionDO> getAllSessionDO(int page, int perPage) throws ServiceException;

	SessionDetailedDO getSessionDetailedDO(Long id) throws ServiceException;

	Session getSession(Long id) throws ServiceException;
}
