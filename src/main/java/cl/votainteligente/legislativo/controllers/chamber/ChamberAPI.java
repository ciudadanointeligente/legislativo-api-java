package cl.votainteligente.legislativo.controllers.chamber;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.ChamberDO;

public interface ChamberAPI {
	/**
	 * Returns all the chambers.
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of chambers to be retrieved in a page.
	 * @return A Page of Chamber Abstracts (ChamberDO)
	 * @see ChamberDO
	 */
	@Path("all")
	@GET
	Page<ChamberDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a chamber.
	 * 
	 * @param id
	 *            The Chamber identification number.
	 * @return Detailed of a chamber
	 * @see ChamberDO
	 * @throws ResourceNotFoundException
	 */
	@Path("any")
	@GET
	ChamberDO getById(@PathParam("id") final long id);

}