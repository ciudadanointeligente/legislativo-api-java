package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.DO.ChamberDO;

public interface ChamberService {
	Chamber newChamber(Chamber chamber) throws ServiceException;

	Chamber getById(Long id) throws ServiceException;

	ChamberDO getChamberDOById(Long id) throws ServiceException;

	Page<ChamberDO> getAllChambersDO(int page, int perPage)
			throws ServiceException;
}
