package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Commune;

public interface CommuneService {

	Commune newCommune(Commune commune) throws ServiceException;

	List<Commune> getAllCommunes() throws ServiceException;

	List<Commune> findCommunesByName(String name) throws ServiceException;

	Commune getCommunes() throws ServiceException;
}
