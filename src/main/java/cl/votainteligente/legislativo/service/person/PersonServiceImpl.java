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

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonDO> findPersonsByFirstName(String firstName) {
		Query query = getEntityManager()
				.createQuery(
						"select p from Person p where upper(p.firstName) like upper(?)");
		query.setParameter(1, "%" + firstName + "%");
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		return listDO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonDO> findPersonsByLastName(String lastName) {
		Query query = getEntityManager().createQuery(
				"select p from Person p where upper(p.lastName) like upper(?)");
		query.setParameter(1, "%" + lastName + "%");
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		return listDO;
	}

	@Override
	public Person getPerson(Long id) throws ServiceException {
		return getEntityManager().find(Person.class, id);
	}

	@Override
	public List<PersonDO> getKPersonDOs(int page, int k)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Person p");
		query.setFirstResult(page * (k - 1));
		query.setMaxResults(k);
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		return listDO;
	}
}