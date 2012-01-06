package cl.votainteligente.legislativo.controllers.agrupation;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.AgrupationDO;
import cl.votainteligente.legislativo.model.domainobjects.AgrupationDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

public interface AgrupationAPI {
	/**
	 * Returns all the agrupations.
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of parties to be retrieved in a page (optional).
	 * @return A Page of Agrupation Abstracts (AgrupationDO) <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/all"
	 *         >agrupation/all</a>
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
	 * @param name
	 *            The Agrupation name to look for.
	 * @param page
	 *            The number of desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of parties to be retrieved in a page (optional).
	 * @return A page of Agrupation Abstracts (AgrupationDO) <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/any?name=Partido"
	 *         >agrupation/any?name=Partido</a>
	 */
	@GET
	@Path("any")
	public Page<AgrupationDO> getByName(@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the agrupations where a given person is a member.
	 * 
	 * @param page
	 *            The number of desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of parties to be retrieved in a page (optional).
	 * @return A page of Agrupation Abstracts (AgrupationDO) <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/any?person=1"
	 *         >agrupation/any?person=1</a>
	 */

	@GET
	@Path("any")
	public Page<AgrupationDO> getByPerson(@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a agrupation.
	 * 
	 * @param id
	 *            The Agrupation identification number.
	 * @return Detailed of an agrupation <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/any?id=1"
	 *         >agrupation/any?id=1</a>
	 * @see AgrupationDetailedDO
	 */
	@GET
	@Path("any")
	public AgrupationDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Gives all the historical affiliates (person) of a agrupation.
	 * 
	 * @param id
	 *            The Agrupation identification number.
	 * @param page
	 *            The number of desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (Person DO) <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/historicalAffiliates?id=1"
	 *         >agrupation/historicalAffiliates?id=1</a>
	 */
	@GET
	@Path("historicalAffiliates")
	public Page<PersonDO> getHistoricalAffiliatesByAgrupation(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Gives all the current affiliates (person) of a agrupation.
	 * 
	 * @param id
	 *            The Agrupation identification number.
	 * @param page
	 *            The number of desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (Person DO) <br />
	 *         For Example:
	 * 
	 *         <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/agrupation/currentAffiliates?id=1"
	 *         >agrupation/currentAffiliates?id=1</a>
	 */
	@GET
	@Path("currentAffiliates")
	public Page<PersonDO> getCurrentAffiliatesByAgrupation(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}
