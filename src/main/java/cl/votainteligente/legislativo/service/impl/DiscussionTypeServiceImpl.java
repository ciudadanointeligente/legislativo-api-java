package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.DiscussionType;
import cl.votainteligente.legislativo.service.DiscussionTypeService;
import cl.votainteligente.legislativo.service.EntityManagerService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.Query;

@Service
public class DiscussionTypeServiceImpl extends EntityManagerService implements DiscussionTypeService {

	@Override
	public DiscussionType getDisscussionType(Long id) throws ServiceException {
		return getEntityManager().find(DiscussionType.class, id);
	}

	@Override
	public Page<DiscussionType> getAll(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from DiscussionType p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<DiscussionType> discussionTypeList = query.getResultList();

		query = getEntityManager().createQuery("select count(p) from DiscussionType p");
		long total = (Long) query.getSingleResult();

		return new Page<DiscussionType>(discussionTypeList, page, perPage, total);

	}

}
