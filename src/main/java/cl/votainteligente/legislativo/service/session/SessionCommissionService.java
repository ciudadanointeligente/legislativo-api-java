package cl.votainteligente.legislativo.service.session;

import java.util.Date;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.SessionCommission;
import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDetailedDO;

public interface SessionCommissionService {

	Page<SessionCommissionDO> getAllSessionCommissionDO(int page, int perPage)
			throws ServiceException;

	SessionCommissionDetailedDO getSessionCommissionDetailedDO(Long id)
			throws ServiceException;

	SessionCommission getSessionCommission(Long id) throws ServiceException;

	Page<SessionCommissionDO> getByDateRange(Date from, Date to, int page,
			int perPage) throws ServiceException;

	Page<SessionCommissionDO> getByLegislature(Long id, int page, int perPage)
			throws ServiceException;
}