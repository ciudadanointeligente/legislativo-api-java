package cl.votainteligente.legislativo.service.bill;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class BillServiceImpl extends EntityManagerService implements
		BillService {

	@Override
	public Bill newBill(Bill bill) throws ServiceException {
		getEntityManager().persist(bill);
		return bill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BillDO> getAllBillDOs() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Bill p");
		List<BillDO> listDO = new ArrayList<BillDO>();
		for (Bill bill : (List<Bill>) query.getResultList()) {
			listDO.add(bill.asDomainObject());
		}
		return listDO;
	}

	@Override
	public Bill getBill(Long id) throws ServiceException {
		return getEntityManager().find(Bill.class, id);
	}
}
