package cl.votainteligente.legislativo.service.bill;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.StageDescription;

public interface StageDescriptionService {
	List<StageDescription> getAll() throws ServiceException;

	StageDescription getById(Long id) throws ServiceException;
}
