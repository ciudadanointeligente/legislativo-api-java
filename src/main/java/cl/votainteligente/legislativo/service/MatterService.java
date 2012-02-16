package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Matter;

public interface MatterService {

	Matter newMatter(Matter matter) throws ServiceException;

	Matter getById(Long id) throws ServiceException;

	Page<Matter> findByName(String name, int pageNumber, int resultsPerPage)
			throws ServiceException;

	Page<Matter> getAll(int pageNumber, int resultsPerPage)
			throws ServiceException;
}
