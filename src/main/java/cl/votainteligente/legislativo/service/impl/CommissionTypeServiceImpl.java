package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.service.CommissionTypeService;
import cl.votainteligente.legislativo.service.EntityManagerService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.Query;

@Service
public class CommissionTypeServiceImpl extends EntityManagerService implements CommissionTypeService {

	@Override
	public CommissionType getById(Long id) throws ServiceException {
		return getEntityManager().find(CommissionType.class, id);
	}

	@Override
	public List<CommissionType> getAll() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from CommissionType p");
		List<CommissionType> commissionTypeList = query.getResultList();
		return commissionTypeList;
	}

}
