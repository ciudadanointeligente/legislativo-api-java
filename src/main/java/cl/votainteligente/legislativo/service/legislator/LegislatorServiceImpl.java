package cl.votainteligente.legislativo.service.legislator;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class LegislatorServiceImpl extends EntityManagerService implements
		LegislatorService {

	@Override
	public Legislator getLegislator(Long id) throws ServiceException {
		return getEntityManager().find(Legislator.class, id);
	}

	@Override
	public Legislator newLegislator(Legislator legislator)
			throws ServiceException {
		getEntityManager().persist(legislator);
		return legislator;
	}

}
