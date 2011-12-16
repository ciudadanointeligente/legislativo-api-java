package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
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
	public Page<Region> getAllRegions(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Region p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Region> list = query.getResultList();
		query = getEntityManager().createQuery("select count(p) from Region p");
		long total = (Long) query.getSingleResult();
		return new Page<Region>(list, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Region> findRegionsByName(String name, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Region p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Region> list = query.getResultList();
		query = getEntityManager().createQuery(
				"select p from Region p where upper(p.name) like upper(?)");
		long total = (Long) query.getSingleResult();
		return new Page<Region>(list, page, perPage, total);
	}

	@Override
	public Region getRegion(Long id) throws ServiceException {
		return getEntityManager().find(Region.class, id);
	}
}
