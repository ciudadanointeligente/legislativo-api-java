package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Region;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.RegionService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.Query;

@Service
public class RegionServiceImpl extends EntityManagerService implements RegionService {

	@Override
	public Region newRegion(Region region) throws ServiceException {
		getEntityManager().persist(region);
		return region;
	}

	@Override
	public Page<Region> getAllRegions(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Region p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Region> regionList = query.getResultList();
		query = getEntityManager().createQuery("select count(p) from Region p");
		long total = (Long) query.getSingleResult();
		return new Page<Region>(regionList, page, perPage, total);
	}

	@Override
	public Page<Region> findRegionsByName(String name, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Region p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Region> regionList = query.getResultList();
		query = getEntityManager().createQuery("select count(p) from Region p where upper(p.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		long total = (Long) query.getSingleResult();
		return new Page<Region>(regionList, page, perPage, total);
	}

	@Override
	public Region getRegion(Long id) throws ServiceException {
		return getEntityManager().find(Region.class, id);
	}
}
