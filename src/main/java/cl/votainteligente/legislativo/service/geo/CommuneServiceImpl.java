package cl.votainteligente.legislativo.service.geo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
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
	public Page<Commune> getAllCommunes(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Commune p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Commune> list = query.getResultList();
		query = getEntityManager()
				.createQuery("select count(p) from Commune p");
		long total = (Long) query.getSingleResult();
		return new Page<Commune>(list, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Commune> findCommunesByName(String name, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Commune p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Commune> list = query.getResultList();
		query = getEntityManager()
				.createQuery(
						"select count(p) from Commune p where upper(p.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		long total = (Long) query.getSingleResult();
		return new Page<Commune>(list, page, perPage, total);
	}

	@Override
	public Commune getCommune(Long id) throws ServiceException {
		return getEntityManager().find(Commune.class, id);
	}

	@Override
	public Page<CommuneDO> getAllCommuneDOs(int page, int perPage)
			throws ServiceException {
		return CommuneToCommuneDO(getAllCommunes(page, perPage));
	}

	@Override
	public Page<CommuneDO> findCommuneDOsByName(String name, int page,
			int perPage) throws ServiceException {
		return CommuneToCommuneDO(findCommunesByName(name, page, perPage));
	}

	@Override
	public CommuneDO getCommuneDO(Long id) throws ServiceException {
		return getCommune(id).asDomainObject();
	}

	private Page<CommuneDO> CommuneToCommuneDO(Page<Commune> page) {
		List<CommuneDO> listDO = new LinkedList<CommuneDO>();
		for (Commune c : page.getElements())
			listDO.add(c.asDomainObject());
		Page<CommuneDO> ans = new Page<CommuneDO>();
		ans.setElements(listDO);
		ans.setPageNumber(page.getPageNumber());
		ans.setTotalElements(page.getTotalElements());
		ans.setTotalPages(page.getTotalPages());
		return ans;
	}
}
