package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.*;

import javax.ws.rs.*;

public interface LegislatorRoleAPI {

	/**
	 * Allows you to get all the information of a legislative period.
	 *
	 * @param id Legislative Period identification number.
	 * @return Detailed view of a legislative period (LegislatorDetailedDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislatorRole/period?id=1">legislatorRole/period?id=1</a>
	 * @see LegislatorDetailedDO
	 */
	@Path("period")
	@GET
	public LegislatorDetailedDO getLegislatorById(@PathParam("id") final long id);

	/**
	 * Returns all the legislative period that currently are in a particular
	 * district.
	 *
	 * @param id District identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of legislative period to be retrieved in a page (optional).
	 * @return A Page of Legislator Abstracts (LegislatorDO)<br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislatorRole/district?id=1&page=1&perPage=10">legislatorRole/district?id=1&page=1&perPage=10</a>
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
	 * @param id Circunscription identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of legislative period to be retrieved in a page (optional).
	 * @return A Page of Legislator Abstracts (LegislatorDO)<br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislatorRole/circunscription?id=1&page=1&perPage=10">legislatorRole/circunscription?id=1&page=1&perPage=10</a>
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
	 * @param id Person identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of legislative period to be retrieved in a page (optional).
	 * @return A Page of Legislator Abstracts (LegislatorDO)<br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislatorRole/person?id=1&page=1&perPage=10">legislatorRole/person?id=1&page=1&perPage=10</a>
	 * @see LegislatorDO
	 */
	@Path("person")
	@GET
	public Page<LegislatorDO> getLegislatorsByPerson(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the people that at least has one legislator role associated. They might not be currently a legislator.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of legislative period to be retrieved in a page (optional).
	 * @return A page of personDOs.<br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislatorRole/people?page=1&perPage=10">legislatorRole/people?page=1&perPage=10</a>
	 * @see PersonDO
	 */
	@Path("all")
	@GET
	public Page<PersonDO> getAllLegislators(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
	/**
	 * Returns all the people that are current legislators.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of legislative period to be retrieved in a page (optional).
	 * @return A page of personPartyDOs.<br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/legislatorRole/all?page=1&perPage=10">legislatorRole/all?page=1&perPage=10</a>
	 * @see PersonPartyDO
	 */
	@Path("current")
	@GET
	public Page<PersonDO> getCurrentLegislators(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}