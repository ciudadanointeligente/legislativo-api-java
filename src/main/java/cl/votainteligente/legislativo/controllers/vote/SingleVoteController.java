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
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.domainobjects.SingleVoteDetailedDO;
import cl.votainteligente.legislativo.service.person.PersonService;
import cl.votainteligente.legislativo.service.vote.SingleVoteService;
import cl.votainteligente.legislativo.service.vote.VoteService;

@Path("singleVote")
@Controller
public class SingleVoteController implements SingleVoteAPI {

	@Autowired
	SingleVoteService singleVoteService;

	@Autowired
	PersonService personService;

	@Autowired
	VoteService voteService;

	@RequestMapping(value = "singleVote/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SingleVoteDetailedDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return singleVoteService.getAllSingleVoteDetailedDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "singleVote/legislator", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SingleVoteDetailedDO> getAllByPerson(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Person person = personService.getPerson(id);
			if (person == null)
				throw new ResourceNotFoundException();
			return singleVoteService.getAllByPerson(person, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "singleVote/vote", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SingleVoteDetailedDO> getAllByVote(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Vote vote = voteService.getVote(id);
			if (vote == null)
				throw new ResourceNotFoundException();
			return singleVoteService.getAllByVote(vote, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "singleVote/any", method = RequestMethod.GET)
	@ResponseBody
	public final SingleVoteDetailedDO getAllById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			SingleVote singleVote = singleVoteService.getSingleVote(id);
			if (singleVote == null)
				throw new ResourceNotFoundException();
			return singleVote.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
