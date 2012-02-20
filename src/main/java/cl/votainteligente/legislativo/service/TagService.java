package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Tag;

public interface TagService {
	Tag getTag(Long id) throws ServiceException;
	Page<Tag> getAll(int page, int perPage) throws ServiceException;
	Page<Tag> findByName(String name, int page, int perPage) throws ServiceException;
}
