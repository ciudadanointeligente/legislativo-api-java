package cl.votainteligente.legislativo.service.geo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class DistrictServiceImpl extends EntityManagerService implements
		DistrictService {

	@Override
	public District newDistrict(District district) throws ServiceException {
		getEntityManager().persist(district);
		return district;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<District> getAllDistricts(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery("select p from District p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<District> list = query.getResultList();
		query = getEntityManager().createQuery(
				"select count(p) from District p");
		long total = (Long) query.getSingleResult();
		return new Page<District>(list, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<District> findDistrictsByName(String name, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from District p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<District> list = query.getResultList();
		query = getEntityManager()
				.createQuery(
						"select count(p) from District p where upper(p.name) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		long total = (Long) query.getSingleResult();
		return new Page<District>(list, page, perPage, total);
	}

	@Override
	public District getDistrict(Long id) throws ServiceException {
		return getEntityManager().find(District.class, id);
	}

	@Override
	public Page<DistrictDO> getAllDistrictDOs(int page, int perPage)
			throws ServiceException {
		return DistrictToDistrictDO(getAllDistricts(page, perPage));
	}

	@Override
	public Page<DistrictDO> findDistrictDOsByName(String name, int page,
			int perPage) throws ServiceException {
		return DistrictToDistrictDO(findDistrictsByName(name, page, perPage));
	}

	@Override
	public DistrictDO getDistrictDO(Long id) throws ServiceException {
		return getDistrict(id).asDomainObject();
	}

	private Page<DistrictDO> DistrictToDistrictDO(Page<District> page) {
		List<DistrictDO> listDO = new LinkedList<DistrictDO>();
		for (District c : page.getContent())
			listDO.add(c.asDomainObject());
		Page<DistrictDO> ans = new Page<DistrictDO>();
		ans.setContent(listDO);
		ans.setPageNumber(page.getPageNumber());
		ans.setTotalElements(page.getTotalElements());
		ans.setTotalPages(page.getTotalPages());
		return ans;
	}
}
