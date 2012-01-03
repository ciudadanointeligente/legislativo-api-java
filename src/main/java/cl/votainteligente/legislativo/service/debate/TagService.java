package cl.votainteligente.legislativo.service.debate;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Tag;

public interface TagService {
	Tag getTag(Long id) throws ServiceException;

	Page<Tag> getAll(int page, int perPage) throws ServiceException;

	Page<Tag> findByName(String name, int page, int perPage)
			throws ServiceException;
}
