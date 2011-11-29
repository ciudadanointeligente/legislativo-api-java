package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.District;

public interface DistrictService {
	District newDistrict(District district) throws ServiceException;
	List<District> getAllDistricts() throws ServiceException;
	List<District> findDistrictsByName(String name) throws ServiceException;
	District getDistricts() throws ServiceException;
}
