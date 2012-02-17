package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.*;
import cl.votainteligente.legislativo.model.DO.BillDO;
import cl.votainteligente.legislativo.model.DO.BillDetailedDO;
import cl.votainteligente.legislativo.model.DO.BillRoleDO;

import java.util.Date;
import java.util.List;

public interface BillService {
	Bill newBill(Bill bill) throws ServiceException;
	List<Bill> getAllBills() throws ServiceException;
	Page<BillDO> getAllBillDOs(int page, int resultsPerPage) throws ServiceException;
	Bill getBill(Long id) throws ServiceException;
	BillDetailedDO getBillDetailedDO(Long id) throws ServiceException;
	Page<BillDO> getByDateRange(Date from, Date to, int page, int resultsPerPage) throws ServiceException;
	Page<BillDO> getByStage(StageDescription stageDescription, int page, int resultsPerPage) throws ServiceException;
	Page<BillDO> getByAuthor(Person person, int page, int resultsPerPage) throws ServiceException;
	Page<BillDO> getByMatter(Matter matter, int page, int resultsPerPage) throws ServiceException;
	Page<BillRoleDO> getByAuthorRole(Person p, int page, int perPage) throws ServiceException;
}