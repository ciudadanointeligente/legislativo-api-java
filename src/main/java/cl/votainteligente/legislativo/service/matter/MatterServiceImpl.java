package cl.votainteligente.legislativo.service.matter;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class MatterServiceImpl extends EntityManagerService implements
		MatterService {

	@Override
	public Matter getById(Long id) throws ServiceException {
		return getEntityManager().find(Matter.class, id);
	}

	@Override
	public Page<Matter> getAll(int pageNumber, int resultsPerPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Matter p");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		@SuppressWarnings("unchecked")
		List<Matter> list = query.getResultList();
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Matter p");
		Long totalMatters = (Long) queryCount.getSingleResult();
		return new Page<Matter>(list, pageNumber, resultsPerPage, totalMatters);
	}

	@Override
	public Matter newMatter(Matter matter) throws ServiceException {
		getEntityManager().persist(matter);
		return matter;
	}

	@Override
	public Page<Matter> findByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Matter p where upper(p.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		@SuppressWarnings("unchecked")
		List<Matter> list = query.getResultList();
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Matter p where upper(p.name) like upper(?)");
		queryCount.setParameter(1, "%" + name + "%");
		Long totalMatters = (Long) queryCount.getSingleResult();
		return new Page<Matter>(list, pageNumber, resultsPerPage, totalMatters);
	}

}
