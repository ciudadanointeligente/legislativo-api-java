package cl.votainteligente.legislativo.service.legislator;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Legislator;

public interface LegislatorService {
	Legislator newLegislator(Legislator legislator) throws ServiceException;

	Legislator getLegislator(Long id) throws ServiceException;
}
