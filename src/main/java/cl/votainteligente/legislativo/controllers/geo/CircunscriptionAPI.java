package cl.votainteligente.legislativo.controllers.geo;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.CircunscriptionDO;

public interface CircunscriptionAPI {

	/**
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of circunscriptions to be retrieved in a page.
	 * @return A Page of Circunscription Abstracts (CircunscriptionDO)
	 * @see CircunscriptionDO
	 */
	@Path("all")
	@GET
	Page<CircunscriptionDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the Circunscriptions with a specific name.
	 * 
	 * @param name
	 *            The Circunscription name to look for.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of circunscriptions to be retrieved in a page.
	 * @return A page of Circunscription Abstracts (CircunscriptionDO)
	 * @see CircunscriptionDO
	 */
	@Path("any")
	@GET
	Page<CircunscriptionDO> findCircunscriptionsByName(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a Circunscription.
	 * 
	 * @param id
	 *            The Circunscription identification number.
	 * @return Detailed of a Circunscription
	 * @throws ResourceNotFoundException
	 * @see CircunscriptionDO
	 */
	@Path("any")
	@GET
	CircunscriptionDO getCircunscriptionById(@PathParam("id") final long id);

	/**
	 * Returns all the Circunscriptions of a region.
	 * 
	 * @param id
	 *            The Region id to look for.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of circunscriptions to be retrieved in a page.
	 * @return A page of Circunscription Abstracts (CircunscriptionDO)
	 * @see CircunscriptionDO
	 */
	@Path("any")
	@GET
	Page<CircunscriptionDO> getCircunscriptionByRegionId(
			@PathParam("region_id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}