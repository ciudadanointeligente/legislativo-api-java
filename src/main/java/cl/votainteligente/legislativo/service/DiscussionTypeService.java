package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DiscussionType;

public interface DiscussionTypeService {
	DiscussionType getDisscussionType(Long id) throws ServiceException;

	Page<DiscussionType> getAll(int page, int perPage) throws ServiceException;
}
