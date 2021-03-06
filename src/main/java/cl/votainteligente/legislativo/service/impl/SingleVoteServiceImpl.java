package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.DO.SingleVoteDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.SingleVoteService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

@Service
public class SingleVoteServiceImpl extends EntityManagerService implements SingleVoteService {

	@Override
	public SingleVote getSingleVote(Long id) throws ServiceException {
		return getEntityManager().find(SingleVote.class, id);
	}

	@Override
	public SingleVoteDetailedDO getSingleVoteDetailedDO(Long id) throws ServiceException {
		return getSingleVote(id).asDetailedDomainObject();
	}

	@Override
	public Page<SingleVoteDetailedDO> getAllSingleVoteDetailedDO(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select s from SingleVote s");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SingleVoteDetailedDO> singleVoteDetailedDoList = new ArrayList<SingleVoteDetailedDO>();

		for (SingleVote singleVote : (List<SingleVote>) query.getResultList()) {
			singleVoteDetailedDoList.add(singleVote.asDetailedDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(s) from SingleVote s");
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SingleVoteDetailedDO>(singleVoteDetailedDoList, page, perPage, total);
	}

	@Override
	public Page<SingleVoteDetailedDO> getAllByPerson(Person person, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select s from SingleVote s join s.person l where l = ?");
		query.setParameter(1, person);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SingleVoteDetailedDO> singleVoteDetailedDoList = new ArrayList<SingleVoteDetailedDO>();

		for (SingleVote singleVote : (List<SingleVote>) query.getResultList()) {
			singleVoteDetailedDoList.add(singleVote.asDetailedDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(s) from SingleVote s join s.person l where l = ?");
		queryCount.setParameter(1, person);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SingleVoteDetailedDO>(singleVoteDetailedDoList, page, perPage, total);
	}

	@Override
	public Page<SingleVoteDetailedDO> getAllByVote(Vote vote, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select s from SingleVote s join s.vote v where v = ?");
		query.setParameter(1, vote);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SingleVoteDetailedDO> singleVoteDetailedDoList = new ArrayList<SingleVoteDetailedDO>();

		for (SingleVote singleVote : (List<SingleVote>) query.getResultList()) {
			singleVoteDetailedDoList.add(singleVote.asDetailedDomainObject());
		}

		Query queryCount = getEntityManager().createQuery("select count(s) from SingleVote s join s.vote v where v = ?");
		queryCount.setParameter(1, vote);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SingleVoteDetailedDO>(singleVoteDetailedDoList, page, perPage, total);
	}

}
