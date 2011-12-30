package cl.votainteligente.legislativo.controllers.debate;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.DebateDO;
import cl.votainteligente.legislativo.model.domainobjects.DebateDetailedDO;

public interface DebateAPI {
	/**
	 * Allows you to get all the information of a debate. 
	 *
	 * @param id
	 *            The debate identification number
	 * @return Detailed view of a debate (DebateDetailedDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/debate/any?id=1">debate/any?id=1</a>
	 * @see DebateDetailedDO
	 */
	@Path("any")
	@GET
	public abstract DebateDetailedDO getDebateById(
			@PathParam("id") final long id);

	/**
	 * Gives all the debates presented between two dates.
	 *
	 * @param from
	 *            Beggining of the interval, in date format.
	 * @param to
	 *            End of the interval, in date format.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of bills to be retrieved in a page (optional).
	 * @return A Page of Debate Abstracts (DebateDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/debate/dateRange?from=01-01-2011&to=01-01-2012&page=1&perPage=10">debate/dateRange?from=01-01-2011&to=01-01-2012&page=1&perPage=10</a>
	 * @see DebateDO
	 */
	@Path("dateRange")
	@GET
	public abstract Page<DebateDO> getDateRange(
			@PathParam("from") final String fromString,
			@PathParam("to") final String toString,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the debates registered in the system of a particular bill.
	 *
	 * @param id
	 *            The bill identification number
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of bills to be retrieved in a page (optional).
	 * @return A Page of Debate Abstracts (DebateDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/debate/bill?id=1&page=1&perPage=10">debate/bill?id=1&page=1&perPage=10</a>
	 * @see DebateDO
	 */
	@Path("bill")
	@GET
	public abstract Page<DebateDO> getDebateByBill(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}