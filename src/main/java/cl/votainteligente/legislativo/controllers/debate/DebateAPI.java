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
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Debate Abstracts (DebateDO)
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
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Debate Abstracts (DebateDO)
	 * @see DebateDO
	 */
	@Path("bill")
	@GET
	public abstract Page<DebateDO> getDebateByBill(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}