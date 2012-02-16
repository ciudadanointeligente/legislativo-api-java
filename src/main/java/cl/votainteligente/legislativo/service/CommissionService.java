package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.model.DO.CommissionDO;
import cl.votainteligente.legislativo.model.DO.CommissionDetailedDO;

public interface CommissionService {

	Commission newCommission(Commission commission) throws ServiceException;

	Page<CommissionDO> getAllCommissions(int pageNumber, int resultsPerPage)
			throws ServiceException;

	Page<CommissionDO> findCommissionsByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException;

	Commission getCommissionById(Long id) throws ServiceException;

	Page<CommissionDO> getCommissionsByType(CommissionType commissionType,
			int pageNumber, int resultsPerPage);

	Page<CommissionDO> getCommissionsByChamber(Chamber chamber, int pageNumber,
			int resultsPerPage);

	CommissionDetailedDO getCommissionDetailedDO(Long id)
			throws ServiceException;

	CommissionDO getCommissionDO(Long id) throws ServiceException;

}
