package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.domainobjects.CircunscriptionDO;

public interface CircunscriptionService {
	Circunscription newCircunscription(Circunscription circunscription)
			throws ServiceException;

	List<Circunscription> getAllCircunscriptionsByRegion(Long regionId)
			throws ServiceException;

	List<Circunscription> findCircunscriptionsByName(String name)
			throws ServiceException;

	List<Circunscription> getAllCircunscriptions() throws ServiceException;

	Circunscription getCircunscription(Long id) throws ServiceException;

	List<CircunscriptionDO> getAllCircunscriptionDOsByRegion(Long regionId)
			throws ServiceException;

	List<CircunscriptionDO> findCircunscriptionDOsByName(String name)
			throws ServiceException;

	List<CircunscriptionDO> getAllCircunscriptionDOs() throws ServiceException;

	CircunscriptionDO getCircunscriptionDO(Long id) throws ServiceException;
}
