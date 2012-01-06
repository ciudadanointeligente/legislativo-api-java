package cl.votainteligente.legislativo.service.bill;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.StageDescription;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class StageDescriptionServiceImpl extends EntityManagerService implements
		StageDescriptionService {

	@Override
	public StageDescription newStageDescription(
			StageDescription stageDescription) throws ServiceException {
		getEntityManager().persist(stageDescription);
		return stageDescription;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<StageDescription> getAll(int pageNumber, int resultsPerPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from StageDescription p");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<StageDescription> list = query.getResultList();
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from StageDescription p");
		Long totalStageDescriptions = (Long) queryCount.getSingleResult();
		return new Page<StageDescription>(list, pageNumber, resultsPerPage,
				totalStageDescriptions);
	}

	@Override
	public StageDescription getById(Long id) throws ServiceException {
		return getEntityManager().find(StageDescription.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<StageDescription> getByName(String name, int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select p from StageDescription p where upper(p.description) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<StageDescription> list = query.getResultList();
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(p) from StageDescription p where upper(p.description) like upper(?)");
		queryCount.setParameter(1, "%" + name + "%");
		Long totalStageDescriptions = (Long) queryCount.getSingleResult();
		return new Page<StageDescription>(list, pageNumber, resultsPerPage,
				totalStageDescriptions);
	}

}
