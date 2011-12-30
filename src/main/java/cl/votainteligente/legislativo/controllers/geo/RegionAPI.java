package cl.votainteligente.legislativo.controllers.geo;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.model.Region;

public interface RegionAPI {

	/**
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of regions to be retrieved in a page.
	 * @return A Page of Region (Region) <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/geo/region/all"
	 *         >geo/region/all</a>
	 * @see Region
	 */
	@Path("all")
	@GET
	Page<Region> getAll(@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the Regions with a specific name.
	 * 
	 * @param name
	 *            The Region name to look for.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of regions to be retrieved in a page.
	 * @return A page of Region (Region) <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/geo/region/any?name=Magallanes"
	 *         >geo/region/any?name=Magallanes</a>
	 * @see Region
	 */
	@Path("any")
	@GET
	Page<Region> findRegionsByName(@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a Region.
	 * 
	 * @param id
	 *            The Region identification number.
	 * @return Detailed of a Region <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/geo/region/any?id=1"
	 *         >geo/region/any?id=1</a>
	 * @throws ResourceNotFoundException
	 * @see Region
	 */
	@Path("any")
	@GET
	Region getRegionById(@PathParam("id") final long id);

}