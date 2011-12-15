package cl.votainteligente.legislativo.service.geo;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;

public interface DistrictService {
	District newDistrict(District district) throws ServiceException;

	Page<District> getAllDistricts(int page, int perPage) throws ServiceException;

	Page<District> findDistrictsByName(String name, int page, int perPage) throws ServiceException;

	District getDistrict(Long id) throws ServiceException;

	Page<DistrictDO> getAllDistrictDOs(int page, int perPage) throws ServiceException;

	Page<DistrictDO> findDistrictDOsByName(String name, int page, int perPage) throws ServiceException;

	DistrictDO getDistrictDO(Long id) throws ServiceException;
}
