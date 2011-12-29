package cl.votainteligente.legislativo.controllers.agrupation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.CommissionDO;
import cl.votainteligente.legislativo.model.domainobjects.CommissionDetailedDO;


public interface CommissionAPI {
	/**
	 * Returns all the commissions registered in the system.
	 *
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of commissions to be retrieved in a page.
	 * @return A Page of Commissions Abstracts (CommissionDO)
	 * @see CommissionDO
	 */
	@GET
	@Path("all")
	public Page<CommissionDO> getAll(@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * TODO documentation
	 *
	 * @param name
	 * @param page
	 * @param perPage
	 * @return
	 */
	public Page<CommissionDO> findByName(@PathParam("name") final String name,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * TODO documentation
	 *
	 * @param id
	 * @param page
	 * @param perPage
	 * @return
	 */
	public CommissionDetailedDO getCommission(@PathParam("id") final long id,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * TODO documentation
	 *
	 * @param type_id
	 * @param page
	 * @param perPage
	 * @return
	 */
	public Page<CommissionDO> findByType(
			@PathParam("type_id") final long type_id,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * TODO documentation
	 * @param chamber_id
	 * @param page
	 * @param perPage
	 * @return
	 */
	public Page<CommissionDO> findByChamber(
			@PathParam("chamber_id") final long chamber_id,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);
}
