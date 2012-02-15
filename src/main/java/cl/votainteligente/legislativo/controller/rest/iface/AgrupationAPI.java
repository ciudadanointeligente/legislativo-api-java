package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.AgrupationDO;
import cl.votainteligente.legislativo.model.DO.AgrupationDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;

import javax.ws.rs.*;

public interface AgrupationAPI {
	/**
	 * Returns all the agrupations.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of parties to be retrieved in a page (optional).
	 * @return A Page of Agrupation Abstracts (AgrupationDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/all">agrupation/all</a>
	 * @see AgrupationDO
	 */

	@GET
	@Path("all")
	public Page<AgrupationDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the agrupations with a specific name.
	 *
	 * @param name Agrupation name to look for.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of parties to be retrieved in a page (optional).
	 * @return A page of Agrupation Abstracts (AgrupationDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/any?name=Partido">agrupation/any?name=Partido</a>
	 */
	@GET
	@Path("any")
	public Page<AgrupationDO> getByName(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a agrupation.
	 *
	 * @param id Agrupation identification number.
	 * @return Detailed of an agrupation <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/any?id=1">agrupation/any?id=1</a>
	 * @see AgrupationDetailedDO
	 */
	@GET
	@Path("any")
	public AgrupationDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Returns all the agrupations where a given person is a member.
	 *
	 * @param id Person identification number.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of parties to be retrieved in a page (optional).
	 * @return A page of Agrupation Abstracts (AgrupationDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/person?id=1">agrupation/person?id=1</a>
	 */

	@GET
	@Path("person")
	public Page<AgrupationDO> getByPerson(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Gives all the historical members (persons) of a agrupation.
	 *
	 * @param id Agrupation identification number.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (Person DO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/historicalMembers?id=1">agrupation/historicalMembers?id=1</a>
	 */
	@GET
	@Path("historicalMembers")
	public Page<PersonDO> getHistoricalMembersByAgrupation(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Gives all the current members (persons) of a agrupation.
	 *
	 * @param id Agrupation identification number.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (Person DO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/currentMembers?id=1">agrupation/currentMembers?id=1</a>
	 */
	@GET
	@Path("currentMembers")
	public Page<PersonDO> getCurrentMembersByAgrupation(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}
