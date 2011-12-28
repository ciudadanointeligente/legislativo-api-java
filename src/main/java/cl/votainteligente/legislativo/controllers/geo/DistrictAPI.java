package cl.votainteligente.legislativo.controllers.geo;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;

public interface DistrictAPI {

	/**
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of districts to be retrieved in a page.
	 * @return A Page of District Abstracts (DistrictDO)
	 * @see DistrictDO
	 */
	@Path("all")
	@GET
	Page<DistrictDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the Districts with a specific name.
	 * 
	 * @param name
	 *            The District name to look for.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of districts to be retrieved in a page.
	 * @return A page of District Abstracts (DistrictDO)
	 * @see DistrictDO
	 */
	@Path("any")
	@GET
	Page<DistrictDO> findDistrictsByName(@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a District.
	 * 
	 * @param id
	 *            The District identification number.
	 * @return Detailed of a District
	 * @throws ResourceNotFoundException
	 * @see DistrictDO
	 */
	@Path("any")
	@GET
	DistrictDO getDistrictById(@PathParam("id") final long id);

}