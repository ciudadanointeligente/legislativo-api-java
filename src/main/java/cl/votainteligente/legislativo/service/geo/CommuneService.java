package cl.votainteligente.legislativo.service.geo;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Commune;
import cl.votainteligente.legislativo.model.domainobjects.CommuneDO;

public interface CommuneService {
	Commune newCommune(Commune commune) throws ServiceException;

	Page<Commune> getAllCommunes(int page, int perPage) throws ServiceException;

	Page<Commune> findCommunesByName(String name, int page, int perPage)
			throws ServiceException;

	Commune getCommune(Long id) throws ServiceException;

	Page<CommuneDO> getAllCommuneDOs(int page, int perPage)
			throws ServiceException;

	Page<CommuneDO> findCommuneDOsByName(String name, int page, int perPage)
			throws ServiceException;

	CommuneDO getCommuneDO(Long id) throws ServiceException;
}
