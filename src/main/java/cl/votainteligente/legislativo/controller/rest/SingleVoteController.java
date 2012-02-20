package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.common.Constants;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.SingleVoteAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.DO.SingleVoteDetailedDO;
import cl.votainteligente.legislativo.service.PersonService;
import cl.votainteligente.legislativo.service.SingleVoteService;
import cl.votainteligente.legislativo.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("singleVote")
@Controller
public class SingleVoteController implements SingleVoteAPI {

	@Autowired
	SingleVoteService singleVoteService;
	@Autowired
	PersonService personService;
	@Autowired
	VoteService voteService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SingleVote#getAll(int, int)
	 */
	@RequestMapping(value = "singleVote/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SingleVoteDetailedDO> getAll(
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return singleVoteService.getAllSingleVoteDetailedDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SingleVote#getAllByPerson(long, int, int)
	 */
	@RequestMapping(value = "singleVote/legislator", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SingleVoteDetailedDO> getAllByPerson(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Person person = personService.getPerson(id);

			if (person == null) {
				throw new ResourceNotFoundException();
			}

			return singleVoteService.getAllByPerson(person, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SingleVote#getAllByVote(long, int, int)
	 */
	@RequestMapping(value = "singleVote/vote", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SingleVoteDetailedDO> getAllByVote(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Vote vote = voteService.getVote(id);

			if (vote == null) {
				throw new ResourceNotFoundException();
			}

			return singleVoteService.getAllByVote(vote, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.iface.SingleVote#getAllById(long)
	 */
	@RequestMapping(value = "singleVote/any", method = RequestMethod.GET)
	@ResponseBody
	public final SingleVoteDetailedDO getAllById(@RequestParam(value = "id", required = true) final long id) {
		try {
			SingleVote singleVote = singleVoteService.getSingleVote(id);

			if (singleVote == null) {
				throw new ResourceNotFoundException();
			}

			return singleVote.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
