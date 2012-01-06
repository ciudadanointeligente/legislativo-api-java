package cl.votainteligente.legislativo.controllers.vote;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.SingleVoteDO;

public interface SingleVoteAPI {

	/**
	 * Returns all the single votes.
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of single votes to be retrieved in a page (optional).
	 * @return A Page of SingleVote Abstracts (SingleVoteDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/singleVote/all?page=1&perPage=10"
	 *         >singleVote/all?page=1&perPage=10</a>
	 * @see SingleVoteDO
	 */
	@Path("all")
	@GET
	Page<SingleVoteDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the single votes registered by legislator.
	 * 
	 * @param id
	 *            The Person id.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of single votes to be retrieved in a page (optional).
	 * @return A Page of SingleVote Abstracts (SingleVoteDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/singleVote/person?id=1&page=1&perPage=10"
	 *         >singleVote/person?id=1&page=1&perPage=10</a>
	 * @see SingleVoteDO
	 */
	@Path("person")
	@GET
	Page<SingleVoteDO> getAllByPerson(@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the single votes registered in a vote.
	 * 
	 * @param id
	 *            The Vote id.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of single votes to be retrieved in a page (optional).
	 * @return A Page of SingleVote Abstracts (SingleVoteDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/singleVote/vote?id=1&page=1&perPage=10"
	 *         >singleVote/vote?id=1&page=1&perPage=10</a>
	 * @see SingleVoteDO
	 */
	@Path("vote")
	@GET
	Page<SingleVoteDO> getAllByVote(@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a single vote.
	 * 
	 * @param id
	 *            The SingleVote identification number.
	 * @return Detailed of a SingleVote. (SingleVoteDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/singleVote/any?id=1"
	 *         >singleVote/any?id=1</a>
	 * @see SingleVoteDO
	 */
	@Path("any")
	@GET
	SingleVoteDO getAllById(@PathParam("id") final long id);

}