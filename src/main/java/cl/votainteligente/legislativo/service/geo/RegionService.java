package cl.votainteligente.legislativo.service.geo;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Region;
import cl.votainteligente.legislativo.model.domainobjects.Page;

public interface RegionService {
	Region newRegion(Region region) throws ServiceException;

	Page<Region> getAllRegions(int page, int perPage) throws ServiceException;

	Page<Region> findRegionsByName(String name, int page, int perPage)
			throws ServiceException;

	Region getRegion(Long id) throws ServiceException;
}
