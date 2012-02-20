package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.VoteDO;
import cl.votainteligente.legislativo.model.DO.VoteDetailedDO;

import javax.ws.rs.*;

public interface VoteAPI {

	/**
	 * Return all the votes.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of votes to be retrieved in a page (optional).
	 * @return A Page of SingleVote Abstracts (VoteDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/vote/all?page=1&perPage=10">vote/all?page=1&perPage=10</a>
	 * @see VoteDO
	 */
	@Path("all")
	@GET
	Page<VoteDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 *
	 * @param id Session identification number.
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of votes to be retrieved in a page (optional).
	 * @return A Page of SingleVote Abstracts (VoteDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/vote/session?id=1&page=1&perPage=10">vote/session?id=1&page=1&perPage=10</a>
	 * @see VoteDO
	 */
	@Path("session")
	@GET
	Page<VoteDO> getAllBySession(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 *
	 * @param id Result identification number (0 : Approved, 1 : Rejected, 2 : Draw, 3 : No quorum)
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of votes to be retrieved in a page (optional).
	 * @return A Page of SingleVote Abstracts (VoteDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/vote/result?id=0&page=1&perPage=10">vote/result?id=0&page=1&perPage=10</a>
	 * @see VoteDO
	 */
	@Path("result")
	@GET
	Page<VoteDO> getAllByResult(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a vote.
	 *
	 * @param id Vote identification number.
	 * @return Detailed of a Vote. (VoteDetailedDO) <br />
	 * 			For example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/vote/any?id=1">vote/any?id=1</a>
	 * @see VoteDetailedDO
	 */
	@Path("any")
	@GET
	VoteDetailedDO getAllById(@PathParam("id") final long id);

}