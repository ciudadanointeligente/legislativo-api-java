package cl.votainteligente.legislativo.controllers.vote;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Session;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.domainobjects.VoteDO;
import cl.votainteligente.legislativo.model.domainobjects.VoteDetailedDO;
import cl.votainteligente.legislativo.service.session.SessionService;
import cl.votainteligente.legislativo.service.vote.VoteService;

@Path("vote")
@Controller
public class VoteController implements VoteAPI {

	@Autowired
	VoteService voteService;

	@Autowired
	SessionService sessionService;

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controllers.vote.VoteAPI#getAll(int, int)
	 */
	@RequestMapping(value = "vote/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<VoteDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return voteService.getAllVoteDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controllers.vote.VoteAPI#getAllBySession(long, int, int)
	 */
	@RequestMapping(value = "vote/session", method = RequestMethod.GET)
	@ResponseBody
	public final Page<VoteDO> getAllBySession(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Session session = sessionService.getSession(id);
			if (session == null)
				throw new ResourceNotFoundException();
			return voteService.getBySession(session, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controllers.vote.VoteAPI#getAllByResult(long, int, int)
	 */
	@RequestMapping(value = "vote/result", method = RequestMethod.GET)
	@ResponseBody
	public final Page<VoteDO> getAllByResult(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return voteService.getByResult(id, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/* (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controllers.vote.VoteAPI#getAllById(long)
	 */
	@RequestMapping(value = "vote/any", method = RequestMethod.GET)
	@ResponseBody
	public final VoteDetailedDO getAllById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Vote vote = voteService.getVote(id);
			if (vote == null)
				throw new ResourceNotFoundException();
			return vote.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

}
