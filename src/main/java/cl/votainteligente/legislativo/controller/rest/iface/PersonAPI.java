package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.model.DO.PersonDetailedDO;

import javax.ws.rs.*;

public interface PersonAPI {
	/**
	 * Returns all the people registered in the system.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (PersonDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/person/all">person/all</a>
	 * @see PersonDO
	 */
	@Path("all")
	@GET
	public abstract Page<PersonDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Gives all the people registered in the system with the followed fist
	 * name.
	 *
	 * @param firstName Person first name.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (PersonDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/person/any?firstName=Ana">person/any?firstName=Ana</a>
	 * @see PersonDO
	 */
	@Path("any")
	@GET
	public abstract Page<PersonDO> findPersonByFirstName(
			@PathParam("firstName") final String firstName,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Gives all the people registered in the system with the followed last
	 * name.
	 *
	 * @param lastName Person last name.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (PersonDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/person/any?lastName=Girardi">person/any?lastName=Girardi</a>
	 * @see PersonDO
	 */
	@Path("any")
	@GET
	public abstract Page<PersonDO> findPersonByLastName(
			@PathParam("lastName") final String lastName,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a person.
	 *
	 * @param id Person identification number.
	 * @return Detailed of a Person <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/person/any?id=1">person/any?id=1</a>
	 * @throws ResourceNotFoundException
	 * @see PersonDetailedDO
	 */
	@Path("any")
	@GET
	public abstract PersonDetailedDO getPersonById(@PathParam("id") final long id);

}