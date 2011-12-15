package cl.votainteligente.legislativo.service.bill;

import java.util.Date;
import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.StageDescription;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;

public interface BillService {
	Bill newBill(Bill bill) throws ServiceException;

	List<Bill> getAllBills() throws ServiceException;

	Page<BillDO> getAllBillDOs(int page, int resultsPerPage)
			throws ServiceException;

	Bill getBill(Long id) throws ServiceException;

	Page<BillDO> getByDateRange(Date from, Date to, int page, int resultsPerPage)
			throws ServiceException;

	Page<BillDO> getByStage(StageDescription stageDescription, int page,
			int resultsPerPage) throws ServiceException;

	Page<BillDO> getByAuthor(Person person, int page, int resultsPerPage)
			throws ServiceException;

	Page<BillDO> getByMatter(Matter matter, int page, int resultsPerPage)
			throws ServiceException;
}