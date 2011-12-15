package cl.votainteligente.legislativo.service.agrupation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.domainobjects.PartyDO;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class PartyServiceImpl extends EntityManagerService implements
		PartyService {

	@Override
	public Party newParty(Party party) throws ServiceException {
		getEntityManager().persist(party);
		return party;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartyDO> getAllParties() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Party p");
		List<PartyDO> listDO = new ArrayList<PartyDO>();
		for(Party party: (List<Party>) query.getResultList()){
			listDO.add(party.asDomainObject());
		}
		return listDO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartyDO> findPartiesByName(String name) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Party p where upper(p.firstName) like upper(?)");
		query.setParameter(1, "%" + name + "%");
		List<PartyDO> listDO = new ArrayList<PartyDO>();
		for (Party party : (List<Party>) query.getResultList()) {
			listDO.add(party.asDomainObject());
		}
		return listDO;
	}

	@Override
	public Party getParty(Long id) throws ServiceException {
		return getEntityManager().find(Party.class, id);
	}
}
