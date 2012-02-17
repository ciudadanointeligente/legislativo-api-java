package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Session;
import cl.votainteligente.legislativo.model.DO.SessionDO;
import cl.votainteligente.legislativo.model.DO.SessionDetailedDO;

import java.util.Date;

public interface SessionService {
	Page<SessionDO> getAllSessionDO(int page, int perPage) throws ServiceException;
	SessionDetailedDO getSessionDetailedDO(Long id) throws ServiceException;
	Session getSession(Long id) throws ServiceException;
	Page<SessionDO> getByDateRange(Date from, Date to, int page, int perPage) throws ServiceException;
	Page<SessionDO> getByLegislature(Long id, int page, int perPage) throws ServiceException;
}
