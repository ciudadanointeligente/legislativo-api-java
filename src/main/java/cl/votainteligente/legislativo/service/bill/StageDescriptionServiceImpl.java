package cl.votainteligente.legislativo.service.bill;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.StageDescription;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class StageDescriptionServiceImpl extends EntityManagerService implements
		StageDescriptionService {

	@SuppressWarnings("unchecked")
	@Override
	public List<StageDescription> getAll() throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from StageDescription p");
		return query.getResultList();
	}

	@Override
	public StageDescription getById(Long id) throws ServiceException {
		return getEntityManager().find(StageDescription.class, id);
	}

}
