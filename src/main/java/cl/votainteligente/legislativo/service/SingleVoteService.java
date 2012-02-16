package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.DO.SingleVoteDetailedDO;

public interface SingleVoteService {

	Page<SingleVoteDetailedDO> getAllSingleVoteDetailedDO(int page, int perPage)
			throws ServiceException;

	Page<SingleVoteDetailedDO> getAllByPerson(Person person, int page, int perPage)
			throws ServiceException;

	Page<SingleVoteDetailedDO> getAllByVote(Vote vote, int page, int perPage)
			throws ServiceException;

	SingleVote getSingleVote(Long id) throws ServiceException;

	SingleVoteDetailedDO getSingleVoteDetailedDO(Long id) throws ServiceException;

}
