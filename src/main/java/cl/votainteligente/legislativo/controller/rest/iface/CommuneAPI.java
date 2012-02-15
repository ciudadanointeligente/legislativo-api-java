package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.model.DO.CommuneDO;

import javax.ws.rs.*;

public interface CommuneAPI {

	/**
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of communes to be retrieved in a page (optional).
	 * @return A Page of Commune Abstracts (CommuneDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commune/all">commune/all</a>
	 * @see CommuneDO
	 */
	@Path("all")
	@GET
	Page<CommuneDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the communes with a specific name.
	 *
	 * @param name Commune name to look for.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of communes to be retrieved in a page (optional).
	 * @return A page of Commune Abstracts (CommuneDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commune/any?name=Puerto">commune/any?name=Puerto</a>
	 * @see CommuneDO
	 */
	@Path("any")
	@GET
	Page<CommuneDO> findCommunesByName(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a commune.
	 *
	 * @param id Commune identification number.
	 * @return Detailed of a commune <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commune/any?id=1">commune/any?id=1</a>
	 * @throws ResourceNotFoundException
	 * @see CommuneDO
	 */
	@Path("any")
	@GET
	CommuneDO getCommuneById(@PathParam("id") final long id);

}