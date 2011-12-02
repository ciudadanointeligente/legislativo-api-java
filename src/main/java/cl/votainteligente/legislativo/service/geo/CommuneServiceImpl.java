package cl.votainteligente.legislativo.service.geo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Commune;
import cl.votainteligente.legislativo.model.domainobjects.CommuneDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class CommuneServiceImpl extends EntityManagerService implements
		CommuneService {

	@Override
	public Commune newCommune(Commune commune) throws ServiceException {
		getEntityManager().persist(commune);
		return commune;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commune> getAllCommunes() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Commune p");
		List<Commune> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commune> findCommunesByName(String name)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Commune p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		List<Commune> list = query.getResultList();
		return list;
	}

	@Override
	public Commune getCommune(Long id) throws ServiceException {
		return getEntityManager().find(Commune.class, id);
	}

	@Override
	public List<CommuneDO> getAllCommuneDOs() throws ServiceException {
		return CommuneToCommuneDO(getAllCommunes());
	}

	@Override
	public List<CommuneDO> findCommuneDOsByName(String name)
			throws ServiceException {
		return CommuneToCommuneDO(findCommunesByName(name));
	}

	@Override
	public CommuneDO getCommuneDO(Long id) throws ServiceException {
		return getCommune(id).asDomainObject();
	}

	private List<CommuneDO> CommuneToCommuneDO(List<Commune> list) {
		List<CommuneDO> listDO = new LinkedList<CommuneDO>();
		for (Commune c : list)
			listDO.add(c.asDomainObject());
		return listDO;
	}
}
