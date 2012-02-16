package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.SessionCommissionDO;
import cl.votainteligente.legislativo.model.DO.SessionCommissionDetailedDO;

import javax.ws.rs.*;

public interface SessionCommissionAPI {

	/**
	 * Returns all the sessions.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionCommissionDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionCommission/all?page=1&perPage=10">sessionCommission/all?page=1&perPage=10</a>
	 * @see SessionCommissionDO
	 */
	@Path("all")
	@GET
	Page<SessionCommissionDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a session.
	 *
	 * @param id Session identification number.
	 * @return Detailed of a session <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionCommission/any?id=1">sessionCommission/any?id=1</a>
	 * @see SessionCommissionDetailedDO
	 */
	@Path("any")
	@GET
	SessionCommissionDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Returns all the sessions between two dates.
	 *
	 * @param from Beggining of the interval, in date format (dd-mm-yyyy).
	 * @param to End of the interval, in date format (dd-mm-yyyy).
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionCommissionDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionCommission/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage=10">sessionCommission/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage= 10</a>
	 * @see SessionCommissionDO
	 */
	@Path("dateRange")
	@GET
	Page<SessionCommissionDO> getDateRange(
			@PathParam("from") final String from,
			@PathParam("to") final String to,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the sessions by Legislature number.
	 *
	 * @param id Legislature identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionCommissionDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionCommission/legislature?id=1&page=1&perPage=10">sessionCommission/legislature?id=1&page=1&perPage=10</a>
	 * @see SessionCommissionDO
	 */
	@Path("legislature")
	@GET
	Page<SessionCommissionDO> getByLegislature(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}