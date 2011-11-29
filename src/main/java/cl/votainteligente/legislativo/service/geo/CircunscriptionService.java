package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;

public interface CircunscriptionService {
	Circunscription newCircunscription(Circunscription circunscription) throws ServiceException;
	List<Circunscription> getAllCircunscriptionsByRegion(Long regionId) throws ServiceException;
	List<Circunscription> findCircunscriptionsByName(String name) throws ServiceException;
	List<Circunscription> getAllCircunscriptions() throws ServiceException;
	Circunscription getCircunscriptions() throws ServiceException;
}
