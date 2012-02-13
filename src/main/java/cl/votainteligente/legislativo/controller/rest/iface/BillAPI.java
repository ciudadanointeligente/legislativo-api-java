package cl.votainteligente.legislativo.controller.rest.iface;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.BillDO;
import cl.votainteligente.legislativo.model.DO.BillDetailedDO;
import cl.votainteligente.legislativo.model.DO.BillRoleDO;

public interface BillAPI {
	/**
	 * Returns all the bills registered in the system.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of bills to be retrieved in a page (optional).
	 * @return A Page of Bill Abstracts (BillDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/bill/all?page=1&perPage=10">bill/all?page=1&perPage=10</a>
	 * @see BillDO
	 */
	@GET
	@Path("all")
	public Page<BillDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a bill.
	 *
	 * @param id
	 *            The Bill identification number.
	 * @return Detailed view of a bill (BillDetailedDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/bill/any?id=1">bill/any?id=1</a>
	 * @see BillDetailedDO
	 */
	@GET
	@Path("any")
	public BillDetailedDO getBillById(@PathParam("id") final long id);

	/**
	 * Gives all the bills presented between two dates.
	 *
	 * @param from
	 *            Beggining of the interval, in date format (dd-MM-yyyy).
	 * @param to
	 *            End of the interval, in date format (dd-MM-yyyy).
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of bills to be retrieved in a page (optional).
	 * @return A Page of Bill Abstracts (BillDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/bill/dateRange?from=01-01-2011&to=31-12-2011&page=1&perPage=10">bill/dateRange?from=01-01-2011&to=01-01-2012&page=1&perPage=10</a>
	 * @see BillDO
	 */
	@GET
	@Path("dateRange")
	public Page<BillDO> getDateRange(
			@PathParam("from") final String from,
			@PathParam("to") final String to,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the bills that currently are in a particular stage.
	 *
	 * @param stage_id
	 *            The Stage identification number.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of bills to be retrieved in a page (optional).
	 * @return A Page of Bill Abstracts (BillDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/bill/stage?id=page=1&perPage=10">bill/stage?id=1&page=1&perPage=10</a>
	 * @see BillDO
	 */
	@GET
	@Path("stage")
	public Page<BillDO> getBillsByStage(@PathParam("id") final long stage_id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the bills registered in the system of a particular matter.
	 *
	 * @param author_id
	 *            The matter identificator.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of bills to be retrieved in a page (optional).
	 * @return A Page of Bill Abstracts (BillDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/bill/matter?id=1&page=1&perPage=10">bill/matter?id=1&page=1&perPage=10</a>
	 * @see BillDO
	 */
	@GET
	@Path("matter")
	public Page<BillDO> getByMatter(@PathParam("id") final long author_id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the bills and role (of person) registered in the system of a
	 * particular author
	 *
	 * @param author_id
	 *            The person id of the author
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of bill-roles to be retrieved in a page (optional).
	 * @return A Page of BillRole Abstracts (BillRoleDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/bill/author?id=1&page=1&perPage=10">bill/author?id=1&page=1&perPage=10</a>
	 * @see BillRoleDO
	 */
	@GET
	@Path("author")
	public Page<BillRoleDO> getBillRoleByPerson(
			@PathParam("id") final long author_id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}
