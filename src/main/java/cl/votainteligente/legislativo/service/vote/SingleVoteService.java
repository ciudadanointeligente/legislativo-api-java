package cl.votainteligente.legislativo.service.vote;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.domainobjects.SingleVoteDO;

public interface SingleVoteService {

	Page<SingleVoteDO> getAllSingleVoteDO(int page, int perPage)
			throws ServiceException;

	Page<SingleVoteDO> getAllByPerson(Person person, int page, int perPage)
			throws ServiceException;

	Page<SingleVoteDO> getAllByVote(Vote vote, int page, int perPage)
			throws ServiceException;

	SingleVote getSingleVote(Long id) throws ServiceException;

	SingleVoteDO getSingleVoteDO(Long id) throws ServiceException;

}
