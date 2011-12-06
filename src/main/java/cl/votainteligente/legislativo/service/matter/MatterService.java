package cl.votainteligente.legislativo.service.matter;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Matter;

public interface MatterService {

	Matter newMatter(Matter matter) throws ServiceException;

	Matter getById(Long id) throws ServiceException;

	List<Matter> getAll() throws ServiceException;
}
