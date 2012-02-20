package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Session;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.DO.VoteDO;
import cl.votainteligente.legislativo.model.DO.VoteDetailedDO;

public interface VoteService {
	Page<VoteDO> getAllVoteDO(int page, int perPage) throws ServiceException;
	Page<VoteDO> getBySession(Session session, int page, int perPage) throws ServiceException;
	Page<VoteDO> getByResult(Long result, int page, int perPage) throws ServiceException;
	Vote getVote(Long id) throws ServiceException;
	VoteDetailedDO getVoteDetailedDO(Long id) throws ServiceException;
}
