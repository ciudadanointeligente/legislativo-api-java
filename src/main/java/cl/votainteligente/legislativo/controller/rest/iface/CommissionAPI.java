package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.CommissionDO;
import cl.votainteligente.legislativo.model.DO.CommissionDetailedDO;

import javax.ws.rs.*;

public interface CommissionAPI {
	/**
	 * Returns all the commissions registered in the system.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of commissions to be retrieved in a page (optional).
	 * @return A Page of Commissions Abstracts (CommissionDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commission/all">commission/all</a>
	 * @see CommissionDO
	 */
	@GET
	@Path("all")
	public Page<CommissionDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the commissions find by name.
	 *
	 * @param name Commission name.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of commissions to be retrieved in a page (optional).
	 * @return A Page of Commissions Abstracts (CommissionDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commission/any?name=Relaciones Exteriores">commission/any?name=Relaciones Exteriores</a>
	 * @see CommissionDO
	 */
	@GET
	@Path("any")
	public Page<CommissionDO> findByName(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns detailed information of a particular commission.
	 *
	 * @param id Commission identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of commissions to be retrieved in a page (optional).
	 * @return The information of a Commission <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commission/any?id=448">commission/any?id=448</a>
	 * @see CommisionDetailedDO
	 */
	@GET
	@Path("any")
	public CommissionDetailedDO getCommission(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the Commissions of a particular type.
	 *
	 * @param id Comission type identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of commissions to be retrieved in a page (optional).
	 * @return A page of CommisionDOs.<br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commission/type?id=1">commission/type?id=1</a>
	 * @see ComissionDO
	 */
	@GET
	@Path("type")
	public Page<CommissionDO> findByType(
			@PathParam("type_id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the Commissions of a particular Chamber.
	 *
	 * @param id Chamber identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of commissions to be retrieved in a page (optional).
	 * @return A Page of CommissionDOs <br />
	 * For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/commission/chamber?id=1">commission/chamber?id=1</a>
	 * @see CommissionDO
	 */
	@GET
	@Path("chamber")
	public Page<CommissionDO> findByChamber(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}
