package cl.votainteligente.legislativo.controllers.geo;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.CommuneDO;

public interface CommuneAPI {

	/**
	 *
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of communes to be retrieved in a page.
	 * @return A Page of Commune Abstracts (CommuneDO)
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
	 * @param name
	 *            The Commune name to look for.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of communes to be retrieved in a page.
	 * @return A page of Commune Abstracts (CommuneDO)
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
	 * @param id
	 *            The Commune identification number.
	 * @return Detailed of a commune
	 * @see CommuneDO
	 */
	@Path("any")
	@GET
	CommuneDO getCommuneById(
			@PathParam("id") final long id);

}