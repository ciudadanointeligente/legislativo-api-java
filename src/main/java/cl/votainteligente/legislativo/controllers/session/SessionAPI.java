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
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of sessions to be retrieved in a page.
	 * @return A Page of Session Abstracts (SessionDO)
	 * @see SessionDO
	 */
	@Path("any")
	@GET
	Page<SessionDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a session.
	 * 
	 * @param id
	 *            The Session identification number.
	 * @return Detailed of a session
	 * @see SessionDetailedDO
	 */
	@Path("any")
	@GET
	SessionDetailedDO getById(@PathParam("id") final long id);

}