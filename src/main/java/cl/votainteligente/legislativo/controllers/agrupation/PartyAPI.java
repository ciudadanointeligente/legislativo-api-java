package cl.votainteligente.legislativo.controllers.agrupation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.PartyDO;
import cl.votainteligente.legislativo.model.domainobjects.PartyDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;


public interface PartyAPI {
	/**
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of parties to be retrieved in a page.
	 * @return A Page of Party Abstracts (PartyDO)
	 * @see PartyDO
	 */

	@GET
	@Path("all")
	public Page<PartyDO> getAll(@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * Returns all the parties with a specific name.
	 * 
	 * @param name
	 *            The Party name to look for.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of parties to be retrieved in a page.
	 * @return A page of Party Abstracts (PartyDO)
	 */
	@GET
	@Path("any")
	public Page<PartyDO> getAll(@PathParam("name") final String name,
			@PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * Allows you to get all the information of a party.
	 * 
	 * @param id
	 *            The Party identification number.
	 * @return Detailed of a party
	 * @see PartyDetailedDO
	 */
	@GET
	@Path("any")
	public PartyDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Gives all the historical affiliates (person) of a party.
	 * 
	 * @param id
	 *            The Party identification number.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of people to be retrieved in a page.
	 * @return A Page of Person Abstracts (Person DO)
	 */
	@GET
	@Path("historicalAffiliates")
	public Page<PersonDO> getHistoricalAffiliatesByParty(
			@PathParam("id") final long id, @PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

	/**
	 * Gives all the current affiliates (person) of a party.
	 * 
	 * @param id
	 *            The Party identification number.
	 * @param page
	 *            The number of desired page to be retrieved.
	 * @param perPage
	 *            Amount of people to be retrieved in a page.
	 * @return A Page of Person Abstracts (Person DO)
	 */
	@GET
	@Path("currentAffiliates")
	public Page<PersonDO> getCurrentAffiliatesByParty(
			@PathParam("id") final long id, @PathParam("page") final int page,
			@PathParam("perPage") final int perPage);

}