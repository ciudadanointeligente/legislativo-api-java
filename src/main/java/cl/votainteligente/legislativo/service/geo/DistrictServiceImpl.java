package cl.votainteligente.legislativo.service.geo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;
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
	public List<District> getAllDistricts() throws ServiceException {
		Query query = getEntityManager()
				.createQuery("select p from District p");
		List<District> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> findDistrictsByName(String name)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from District p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		List<District> list = query.getResultList();
		return list;
	}

	@Override
	public District getDistrict(Long id) throws ServiceException {
		return getEntityManager().find(District.class, id);
	}

	@Override
	public List<DistrictDO> getAllDistrictDOs() throws ServiceException {
		return DistrictToDistrictDO(getAllDistricts());
	}

	@Override
	public List<DistrictDO> findDistrictDOsByName(String name)
			throws ServiceException {
		return DistrictToDistrictDO(findDistrictsByName(name));
	}

	@Override
	public DistrictDO getDistrictDO(Long id) throws ServiceException {
		return getDistrict(id).asDomainObject();
	}

	private List<DistrictDO> DistrictToDistrictDO(List<District> list) {
		List<DistrictDO> listDO = new LinkedList<DistrictDO>();
		for (District c : list)
			listDO.add(c.asDomainObject());
		return listDO;
	}
}
