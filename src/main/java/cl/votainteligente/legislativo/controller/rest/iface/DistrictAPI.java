package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.model.DO.DistrictDO;

import javax.ws.rs.*;

public interface DistrictAPI {

	/**
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of districts to be retrieved in a page (optional).
	 * @return A Page of District Abstracts (DistrictDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/district/all">district/all</a>
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
	 * @param name District name to look for.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of districts to be retrieved in a page (optional).
	 * @return A page of District Abstracts (DistrictDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/district/any?name=1">district/any?name=1</a>
	 * @see DistrictDO
	 */
	@Path("any")
	@GET
	Page<DistrictDO> findDistrictsByName(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a District.
	 *
	 * @param id The District identification number.
	 * @return Detailed of a District <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/district/any?id=1">district/any?id=1</a>
	 * @throws ResourceNotFoundException
	 * @see DistrictDO
	 */
	@Path("any")
	@GET
	DistrictDO getDistrictById(@PathParam("id") final long id);

}