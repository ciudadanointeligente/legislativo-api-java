package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.DO.CircunscriptionDO;
import cl.votainteligente.legislativo.service.CircunscriptionService;
import cl.votainteligente.legislativo.service.EntityManagerService;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

@Service
public class CircunscriptionServiceImpl extends EntityManagerService implements CircunscriptionService {

	@Override
	public Circunscription newCircunscription(Circunscription circunscription) throws ServiceException {
		getEntityManager().persist(circunscription);
		return circunscription;
	}

	@Override
	public Page<CircunscriptionDO> getAllCircunscriptionDOsByRegion(Long regionId, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Circunscription p join p.regions r where r.id = ?");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, regionId);
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Circunscription> circunscriptionList = query.getResultList();
		query = getEntityManager().createQuery("select count(p) from Circunscription p join p.regions r where r.id = ?");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, regionId);
		long total = (Long) query.getSingleResult();
		return new Page<CircunscriptionDO>(circunscriptionToCircunscriptionDO(circunscriptionList), page, perPage, total);
	}

	@Override
	public Page<CircunscriptionDO> findCircunscriptionDOsByName(String name, int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Circunscription p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<Circunscription> circunscriptionList = query.getResultList();
		query = getEntityManager().createQuery("select count(p) from Circunscription p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		long total = (Long) query.getSingleResult();
		return new Page<CircunscriptionDO>(circunscriptionToCircunscriptionDO(circunscriptionList), page, perPage, total);
	}

	@Override
	public Page<CircunscriptionDO> getAllCircunscriptionDOs(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Circunscription p");
		List<Circunscription> circunscriptionList = query.getResultList();
		query = getEntityManager().createQuery("select count(p) from Circunscription p");
		long total = (Long) query.getSingleResult();
		return new Page<CircunscriptionDO>(circunscriptionToCircunscriptionDO(circunscriptionList), page, perPage, total);
	}

	@Override
	public CircunscriptionDO getCircunscriptionDO(Long id) throws ServiceException {
		return getCircunscription(id).asDomainObject();
	}

	@Override
	public Circunscription getCircunscription(Long id) throws ServiceException {
		return getEntityManager().find(Circunscription.class, id);
	}

	private List<CircunscriptionDO> circunscriptionToCircunscriptionDO(List<Circunscription> circunscriptionList) {
		List<CircunscriptionDO> circunscriptionDOList = new LinkedList<CircunscriptionDO>();
		for (Circunscription circunscription : circunscriptionList)
			circunscriptionDOList.add(circunscription.asDomainObject());
		return circunscriptionDOList;
	}

}
