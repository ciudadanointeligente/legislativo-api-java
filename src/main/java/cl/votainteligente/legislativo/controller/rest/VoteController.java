package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.common.Constants;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.VoteAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Session;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.DO.VoteDO;
import cl.votainteligente.legislativo.model.DO.VoteDetailedDO;
import cl.votainteligente.legislativo.service.SessionService;
import cl.votainteligente.legislativo.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("vote")
@Controller
public class VoteController implements VoteAPI {

	@Autowired
	VoteService voteService;

	@Autowired
	SessionService sessionService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.VoteAPI#getAll(int, int)
	 */
	@RequestMapping(value = "vote/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<VoteDO> getAll(
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return voteService.getAllVoteDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @seecl.votainteligente.legislativo.controller.rest.iface.VoteAPI#getAllBySession(long, int, int)
	 */
	@RequestMapping(value = "vote/session", method = RequestMethod.GET)
	@ResponseBody
	public final Page<VoteDO> getAllBySession(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Session session = sessionService.getSession(id);

			if (session == null) {
				throw new ResourceNotFoundException();
			}

			return voteService.getBySession(session, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.VoteAPI#getAllByResult(long, int, int)
	 */
	@RequestMapping(value = "vote/result", method = RequestMethod.GET)
	@ResponseBody
	public final Page<VoteDO> getAllByResult(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return voteService.getByResult(id, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.VoteAPI#getAllById(long)
	 */
	@RequestMapping(value = "vote/any", method = RequestMethod.GET)
	@ResponseBody
	public final VoteDetailedDO getAllById(@RequestParam(value = "id", required = true) final long id) {
		try {
			Vote vote = voteService.getVote(id);

			if (vote == null) {
				throw new ResourceNotFoundException();
			}

			return vote.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

}
