package cl.votainteligente.legislativo.service.vote;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;
import cl.votainteligente.legislativo.model.domainobjects.SingleVoteDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class SingleVoteServiceImpl extends EntityManagerService implements
		SingleVoteService {

	@Override
	public SingleVote getSingleVote(Long id) throws ServiceException {
		return getEntityManager().find(SingleVote.class, id);
	}

	@Override
	public SingleVoteDO getSingleVoteDO(Long id) throws ServiceException {
		return getSingleVote(id).asDomainObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<SingleVoteDO> getAllSingleVoteDO(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select s from SingleVote s");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SingleVoteDO> listDO = new ArrayList<SingleVoteDO>();
		for (SingleVote singleVote : (List<SingleVote>) query.getResultList()) {
			listDO.add(singleVote.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(s) from SingleVote s");
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SingleVoteDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<SingleVoteDO> getAllByLegislatorRole(LegislatorRole legislator,
			int page, int perPage) throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select s from SingleVote s join s.legislatorRole l where l = ?");
		query.setParameter(1, legislator);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SingleVoteDO> listDO = new ArrayList<SingleVoteDO>();
		for (SingleVote singleVote : (List<SingleVote>) query.getResultList()) {
			listDO.add(singleVote.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(s) from SingleVote s join s.legislatorRole l where l = ?");
		query.setParameter(1, legislator);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SingleVoteDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<SingleVoteDO> getAllByVote(Vote vote, int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select s from SingleVote s join s.vote v where v = ?");
		query.setParameter(1, vote);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<SingleVoteDO> listDO = new ArrayList<SingleVoteDO>();
		for (SingleVote singleVote : (List<SingleVote>) query.getResultList()) {
			listDO.add(singleVote.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(s) from SingleVote s join s.vote v where v = ?");
		query.setParameter(1, vote);
		Long total = (Long) queryCount.getSingleResult();

		return new Page<SingleVoteDO>(listDO, page, perPage, total);
	}

}
