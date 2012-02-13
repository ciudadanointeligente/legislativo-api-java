package cl.votainteligente.legislativo.controller.rest.iface;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.*;

public interface LegislatorRoleAPI {

	/**
	 * Allows you to get all the information of a legislative period.
	 *
	 * @param id
	 *            The Legislative Period identification number.
	 * @return Detailed view of a legislative period (LegislatorDetailedDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/legislator_role/period?id=1"
	 *         >legislator_role/period?id=1</a>
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
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of legislative period to be retrieved in a page (optional).
	 * @return A Page of Legislator Abstracts (LegislatorDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislator_role/district?id=1&page=1&perPage=10">legislator_role/district?id=1&page=1&perPage=10</a>
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
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of legislative period to be retrieved in a page (optional).
	 * @return A Page of Legislator Abstracts (LegislatorDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislator_role/circunscription?id=1&page=1&perPage=10">legislator_role/circunscription?id=1&page=1&perPage=10</a>
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
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of legislative period to be retrieved in a page (optional).
	 * @return A Page of Legislator Abstracts (LegislatorDO)
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislator_role/person?id=1&page=1&perPage=10">legislator_role/person?id=1&page=1&perPage=10</a>
	 * @see LegislatorDO
	 */
	@Path("person")
	@GET
	public Page<LegislatorDO> getLegislatorsByPerson(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the people in the system that at least contains one legislator role associated. They might not be currently a legislator.
	 *
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 * 			  Amount of legislative period to be retrieved in a page (optional).
	 * @return A page of personDOs.
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislator_role/people?page=1&perPage=10">legislator_role/people?page=1&perPage=10</a>
	 * @see PersonDO
	 */
	@Path("people")
	@GET
	public Page<PersonDO> getKLegislatorPersons(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
	/**
	 * Returns all the people in the system that is CURRENTLY a legislator.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 * 			  Amount of legislative period to be retrieved in a page (optional).
	 * @return A page of personPartyDOs.
	 * <br />
	 * For example:
	 * <a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislator_role/all?page=1&perPage=10">legislator_role/all?page=1&perPage=10</a>
	 * @see PersonPartyDO
	 */
	@Path("all")
	@GET
	public Page<PersonDO> getLegislatorPersons(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}