package cl.votainteligente.legislativo.service.agrupation;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.CommissionType;

public interface CommissionTypeService {
	CommissionType getById(Long id) throws ServiceException;
}
