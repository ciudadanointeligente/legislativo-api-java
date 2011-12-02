package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Commune;
import cl.votainteligente.legislativo.model.domainobjects.CommuneDO;

public interface CommuneService {
	Commune newCommune(Commune commune) throws ServiceException;

	List<Commune> getAllCommunes() throws ServiceException;

	List<Commune> findCommunesByName(String name) throws ServiceException;

	Commune getCommune(Long id) throws ServiceException;

	List<CommuneDO> getAllCommuneDOs() throws ServiceException;

	List<CommuneDO> findCommuneDOsByName(String name) throws ServiceException;

	CommuneDO getCommuneDO(Long id) throws ServiceException;
}
