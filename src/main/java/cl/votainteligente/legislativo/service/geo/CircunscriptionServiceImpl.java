package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;

@Service
public class CircunscriptionServiceImpl implements CircunscriptionService {

	@Override
	public Circunscription newCircunscription(Circunscription circunscription) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Circunscription> getAllCircunscriptionsByRegion(Long regionId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Circunscription> findCircunscriptionsByName(String name) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Circunscription> getAllCircunscriptions() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Circunscription getCircunscriptions() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
