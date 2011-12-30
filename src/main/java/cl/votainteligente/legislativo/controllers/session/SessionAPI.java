package cl.votainteligente.legislativo.controllers.session;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.SessionDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionDetailedDO;

public interface SessionAPI {

	/**
	 * Returns all the sessions.
	 *
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/session/all?page=1&perPage=10"
	 *         >session/all?page=1&perPage=10</a>
	 * @see SessionDO
	 */
	@Path("all")
	@GET
	Page<SessionDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a session.
	 *
	 * @param id
	 *            The Session identification number.
	 * @return Detailed of a session <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/session/any?id=1"
	 *         >session/any?id=1</a>
	 * @see SessionDetailedDO
	 */
	@Path("any")
	@GET
	SessionDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Returns all the sessions between two dates.
	 *
	 * @param from
	 *            Beggining of the interval, in date format (dd-mm-yyyy).
	 * @param to
	 *            End of the interval, in date format (dd-mm-yyyy).
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionDO) <br />
	 *         For example: <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/session/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage=10"
	 *         >session/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage=
	 *         10</a>
	 * @see SessionDO
	 */
	@Path("dateRange")
	@GET
	Page<SessionDO> getDateRange(@PathParam("from") final String fromString,
			@PathParam("to") final String toString,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the sessions by Legislature number.
	 *
	 * @param id
	 *            The id of the Legislature.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/session/legislature?id=1&page=1&perPage=10"
	 *         >session/legislature?id=1&page=1&perPage=10</a>
	 * @see SessionDO
	 */
	@Path("legislature")
	@GET
	Page<SessionDO> getByLegislature(@PathParam("id") final long legislatureId,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}