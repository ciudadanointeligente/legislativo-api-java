package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Session;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.DO.VoteDO;
import cl.votainteligente.legislativo.model.DO.VoteDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.VoteService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

@Service
public class VoteServiceImpl extends EntityManagerService implements VoteService {

	@Override
	public Page<VoteDO> getAllVoteDO(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select v from Vote v");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<VoteDO> voteDOList = new ArrayList<VoteDO>();

		for (Vote vote : (List<Vote>) query.getResultList()) {
			voteDOList.add(vote.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(v) from Vote v");
		Long total = (Long) queryCount.getSingleResult();

		return new Page<VoteDO>(voteDOList, page, perPage, total);
	}

	@Override
	public Page<VoteDO> getBySession(Session session, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select v from Vote v join v.session s where s = ?");
		query.setParameter(1, session);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<VoteDO> voteDOList = new ArrayList<VoteDO>();

		for (Vote vote : (List<Vote>) query.getResultList()) {
			voteDOList.add(vote.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(v) from Vote v join v.session s where s = ?");
		queryCount.setParameter(1, session);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<VoteDO>(voteDOList, page, perPage, total);
	}

	@Override
	public Page<VoteDO> getByResult(Long result, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select v from Vote v where v.result = ?");
		query.setParameter(1, result);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<VoteDO> voteDOList = new ArrayList<VoteDO>();

		for (Vote vote : (List<Vote>) query.getResultList()) {
			voteDOList.add(vote.asDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(v) from Vote v where v.result = ?");
		queryCount.setParameter(1, result);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<VoteDO>(voteDOList, page, perPage, total);
	}

	@Override
	public Vote getVote(Long id) throws ServiceException {
		return getEntityManager().find(Vote.class, id);
	}

	@Override
	public VoteDetailedDO getVoteDetailedDO(Long id) throws ServiceException {
		return getVote(id).asDetailedDomainObject();
	}

}
