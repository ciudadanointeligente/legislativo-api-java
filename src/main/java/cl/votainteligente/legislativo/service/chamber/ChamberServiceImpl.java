package cl.votainteligente.legislativo.service.chamber;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.domainobjects.ChamberDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class ChamberServiceImpl extends EntityManagerService implements
		ChamberService {
	@Override
	public Chamber newChamber(Chamber chamber) throws ServiceException {
		getEntityManager().persist(chamber);
		return chamber;
	}

	@Override
	public Chamber getById(Long id) throws ServiceException {
		return getEntityManager().find(Chamber.class, id);
	}

	@Override
	public ChamberDO getChamberDOById(Long id) throws ServiceException {
		return getById(id).asDomainObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ChamberDO> getAllChambersDO(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("Select c from Chamber c");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<ChamberDO> listDO = new ArrayList<ChamberDO>();
		for (Chamber c : (List<Chamber>) query.getResultList())
			listDO.add(c.asDomainObject());
		Query queryCount = getEntityManager().createQuery(
				"select count(c) from Chamber c");
		Long totalParties = (Long) queryCount.getSingleResult();

		return new Page<ChamberDO>(listDO, page, perPage, totalParties);
	}
}
