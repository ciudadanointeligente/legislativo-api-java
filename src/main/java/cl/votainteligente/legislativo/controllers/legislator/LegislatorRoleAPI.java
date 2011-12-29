package cl.votainteligente.legislativo.controllers.legislator;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDO;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonPartyDO;

public interface LegislatorRoleAPI {

	/**
	 * Allows you to get all the information of a legislative period.
	 * 
	 * @param id
	 *            The Legislative Period identification number.
	 * @return Detailed view of a legislative period (LegislatorDetailedDO)
	 * @throws ResourceNotFoundException
	 * @see LegislatorDetailedDO
	 */
	@Path("period")
	@GET
	public LegislatorDetailedDO getLegislatorById(@PathParam("id") final long id);

	/**
	 * Returns all the legislative period that currently are in a particular
	 * district.
	 * 
	 * @param id
	 *            The District identification number.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of legislative period to be retrieved in a page.
	 * @return A Page of Legislator Abstracts (LegislatorDO)
	 * @see LegislatorDO
	 */
	@Path("district")
	@GET
	public Page<LegislatorDO> getLegislatorByDistrict(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the legislative period that currently are in a particular
	 * circunscription.
	 * 
	 * @param id
	 *            The Circunscription identification number.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of legislative period to be retrieved in a page.
	 * @return A Page of Legislator Abstracts (LegislatorDO)
	 * @see LegislatorDO
	 */
	@Path("circunscription")
	@GET
	public Page<LegislatorDO> getLegislatorByCircunscription(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the legislative period that currently are associated with a
	 * person.
	 * 
	 * @param id
	 *            The Person identification number.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of legislative period to be retrieved in a page.
	 * @return A Page of Legislator Abstracts (LegislatorDO)
	 * @see LegislatorDO
	 */
	@Path("person")
	@GET
	public Page<LegislatorDO> getLegislatorsByPerson(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	@Path("people")
	@GET
	public Page<PersonDO> getKLegislatorPersons(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	@Path("all")
	public Page<PersonPartyDO> getLegislatorPersons(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}