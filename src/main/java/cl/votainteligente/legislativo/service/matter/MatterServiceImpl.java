package cl.votainteligente.legislativo.service.matter;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
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
	public List<Matter> getAll() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Matter p");
		@SuppressWarnings("unchecked")
		List<Matter> list = query.getResultList();
		return list;
	}

	@Override
	public Matter newMatter(Matter matter) throws ServiceException {
		getEntityManager().persist(matter);
		return matter;
	}

}
