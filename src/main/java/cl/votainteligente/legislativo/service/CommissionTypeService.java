package cl.votainteligente.legislativo.service;

import java.util.List;

import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.CommissionType;

public interface CommissionTypeService {
	CommissionType getById(Long id) throws ServiceException;
	List<CommissionType> getAll() throws ServiceException;
}
