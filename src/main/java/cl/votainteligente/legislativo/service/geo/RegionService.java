package cl.votainteligente.legislativo.service.geo;

import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Region;

public interface RegionService {
	Region newRegion(Region region) throws ServiceException;

	List<Region> getAllRegions() throws ServiceException;

	List<Region> findRegionsByName(String name) throws ServiceException;

	Region getRegion(Long id) throws ServiceException;
}
