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
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of chambers to be retrieved in a page (optional).
	 * @return A Page of Chamber Abstracts (ChamberDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/chamber/all?page=1&perPage=10">chamber/all?page=1&perPage=10</a>
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
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/chamber/any?id=1">chamber/any?id=1</a>
	 * @see ChamberDO
	 */
	@Path("any")
	@GET
	ChamberDO getById(@PathParam("id") final long id);

}