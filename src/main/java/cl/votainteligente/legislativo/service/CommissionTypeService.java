package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.CommissionType;

import java.util.List;

public interface CommissionTypeService {
	CommissionType getById(Long id) throws ServiceException;
	List<CommissionType> getAll() throws ServiceException;
}
