package cl.votainteligente.legislativo.service.chamber;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Chamber;

public interface ChamberService {
	Chamber getById(Long id)  throws ServiceException;
	List<Chamber> getChambers()  throws ServiceException;
}
