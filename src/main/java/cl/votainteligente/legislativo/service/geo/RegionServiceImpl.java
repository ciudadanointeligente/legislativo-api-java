package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Region;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class RegionServiceImpl extends EntityManagerService implements
		RegionService {

	@Override
	public Region newRegion(Region region) throws ServiceException {
		getEntityManager().persist(region);
		return region;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> getAllRegions() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Region p");
		List<Region> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> findRegionsByName(String name) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Region p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		List<Region> list = query.getResultList();
		return list;
	}

	@Override
	public Region getRegion(Long id) throws ServiceException {
		return getEntityManager().find(Region.class, id);
	}
}
