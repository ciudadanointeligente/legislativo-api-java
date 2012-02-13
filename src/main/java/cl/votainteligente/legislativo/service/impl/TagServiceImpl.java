package cl.votainteligente.legislativo.service.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Tag;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.TagService;

@Service
public class TagServiceImpl extends EntityManagerService implements TagService {

	@Override
	public Tag getTag(Long id) throws ServiceException {
		return getEntityManager().find(Tag.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Tag> getAll(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Tag p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Tag> list = query.getResultList();
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Tag p");
		Long totalTags = (Long) queryCount.getSingleResult();
		return new Page<Tag>(list, page, perPage, totalTags);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Tag> findByName(String name, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Tag p where upper(p.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Tag> list = query.getResultList();
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Tag p where upper(p.name) like upper(?)");
		queryCount.setParameter(1, "%" + name + "%");
		Long totalTag = (Long) queryCount.getSingleResult();
		return new Page<Tag>(list, page, perPage, totalTag);
	}

}
