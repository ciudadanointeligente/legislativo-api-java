package cl.votainteligente.legislativo.service.bill;

import java.util.List;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;

public interface BillService {
	Bill newBill(Bill bill) throws ServiceException;

	List<BillDO> getAllBillDOs() throws ServiceException;
}