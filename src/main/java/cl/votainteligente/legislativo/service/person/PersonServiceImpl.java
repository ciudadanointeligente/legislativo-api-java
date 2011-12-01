package cl.votainteligente.legislativo.service.person;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.service.EntityManagerService;
import java.util.ArrayList;

@Service
public class PersonServiceImpl extends EntityManagerService implements
		PersonService {

	@Override
	public Person newPerson(Person person) throws ServiceException {
		getEntityManager().persist(person);
		return person;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonDO> getAllPersonDOs() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Person p");
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		return listDO;
	}
}