package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.SessionChamberDO;
import cl.votainteligente.legislativo.model.DO.SessionChamberDetailedDO;

import javax.ws.rs.*;

public interface SessionChamberAPI {

	/**
	 * Returns all the sessions.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionChamberDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/all?page=1&perPage=10">sessionChamber/all?page=1&perPage=10</a>
	 * @see SessionChamberDO
	 */
	@Path("all")
	@GET
	Page<SessionChamberDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a session.
	 *
	 * @param id Session identification number.
	 * @return Detailed of a session <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/any?id=1">sessionChamber/any?id=1</a>
	 * @see SessionChamberDetailedDO
	 */
	@Path("any")
	@GET
	SessionChamberDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Returns all the sessions between two dates.
	 *
	 * @param from Beggining of the interval, in date format (dd-mm-yyyy).
	 * @param to End of the interval, in date format (dd-mm-yyyy).
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionChamberDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage=10">sessionChamber/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage=10</a>
	 * @see SessionChamberDO
	 */
	@Path("dateRange")
	@GET
	Page<SessionChamberDO> getDateRange(
			@PathParam("from") final String from,
			@PathParam("to") final String to,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the sessions by Legislature number.
	 *
	 * @param id Legislature indentification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionChamberDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/legislature?id=1&page=1&perPage=10">sessionChamber/legislature?id=1&page=1&perPage=10</a>
	 * @see SessionChamberDO
	 */
	@Path("legislature")
	@GET
	Page<SessionChamberDO> getByLegislature(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}