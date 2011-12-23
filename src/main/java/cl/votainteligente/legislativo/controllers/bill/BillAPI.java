package cl.votainteligente.legislativo.controllers.bill;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.BillDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.BillRoleDO;

@Path("bill")
public interface BillAPI {
	/**
	 * Returns all the bills registered in the system.
	 *
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("all")
	public Page<BillDO> getAll(@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * Allows you to get all the information of a bill.
	 *
	 * @param id
	 *            The Bill identification number.
	 * @return Detailed view of a bill (BillDetailedDO)
	 * @see BillDetailedDO
	 */
	@GET
	@Path("any")
	public BillDetailedDO getBillById(@PathParam("id") final long id);

	/**
	 * Gives all the bills presented between two dates.
	 *
	 * @param from
	 *            Beggining of the interval, in date format.
	 * @param to
	 *            End of the interval, in date format.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("dataRange")
	public Page<BillDO> getDateRange(
			@PathParam("from") final String fromString,
			@PathParam("to") final String toString,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * Returns all the bills that currently are in a particular stage.
	 *
	 * @param id
	 *            The Stage identification number.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("stage")
	public Page<BillDO> getBillsByStage(@PathParam("id") final long stage_id,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * Returns all the bills registered in the system of a particular matter.
	 *
	 * @param id
	 *            The matter identificator.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("matter")
	public Page<BillDO> getByMatter(@PathParam("id") final long author_id,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * Returns all the bills and role (of person) registered in the system of a
	 * particular author
	 *
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("author_role")
	public Page<BillRoleDO> getBillRoleByPerson(
			@PathParam("id") final long author_id,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);
}
