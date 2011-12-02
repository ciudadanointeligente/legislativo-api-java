package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;

public interface DistrictService {
	District newDistrict(District district) throws ServiceException;

	List<District> getAllDistricts() throws ServiceException;

	List<District> findDistrictsByName(String name) throws ServiceException;

	District getDistrict(Long id) throws ServiceException;

	List<DistrictDO> getAllDistrictDOs() throws ServiceException;

	List<DistrictDO> findDistrictDOsByName(String name) throws ServiceException;

	DistrictDO getDistrictDO(Long id) throws ServiceException;
}
