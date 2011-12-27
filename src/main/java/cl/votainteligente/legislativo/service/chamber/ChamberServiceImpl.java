package cl.votainteligente.legislativo.service.chamber;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class ChamberServiceImpl extends EntityManagerService implements ChamberService{

	@Override
	public Chamber getById(Long id) throws ServiceException {
		return getEntityManager().find(Chamber.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chamber> getChambers() throws ServiceException {
		Query query = getEntityManager().createQuery("Select c from Chamber c");
		return query.getResultList();
	}

}
