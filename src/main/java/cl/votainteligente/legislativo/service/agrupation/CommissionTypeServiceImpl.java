package cl.votainteligente.legislativo.service.agrupation;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class CommissionTypeServiceImpl extends EntityManagerService implements
		CommissionTypeService {

	@Override
	public CommissionType getById(Long id) throws ServiceException {
		return getEntityManager().find(CommissionType.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommissionType> getAll() throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from CommissionType p");
		List<CommissionType> list = query.getResultList();
		return list;
	}

}
