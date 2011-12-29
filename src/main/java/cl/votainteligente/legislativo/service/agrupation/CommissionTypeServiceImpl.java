package cl.votainteligente.legislativo.service.agrupation;

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

}
