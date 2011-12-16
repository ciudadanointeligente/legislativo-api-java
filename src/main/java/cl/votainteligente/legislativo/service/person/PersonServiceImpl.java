package cl.votainteligente.legislativo.service.person;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
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
	public Page<PersonDO> getAllPersonDOs(int page, int perPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Person p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		query = getEntityManager().createQuery("select count(p) from Person p");
		long total = (Long) query.getSingleResult();
		return new Page<PersonDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonDO> findPersonsByFirstName(String firstName, int page,
			int perPage) {
		Query query = getEntityManager()
				.createQuery(
						"select p from Person p where upper(p.firstName) like upper(?)");
		query.setParameter(1, "%" + firstName + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		query = getEntityManager()
				.createQuery(
						"select count(p) from Person p  where upper(p.firstName) like upper(?)");
		query.setParameter(1, "%" + firstName + "%");
		long total = (Long) query.getSingleResult();
		return new Page<PersonDO>(listDO, page, perPage, total);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<PersonDO> findPersonsByLastName(String lastName, int page,
			int perPage) {
		Query query = getEntityManager().createQuery(
				"select p from Person p where upper(p.lastName) like upper(?)");
		query.setParameter(1, "%" + lastName + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> listDO = new ArrayList<PersonDO>();
		for (Person person : (List<Person>) query.getResultList()) {
			listDO.add(person.asDomainObject());
		}
		query = getEntityManager()
				.createQuery(
						"select count(p) from Person p where upper(p.lastName) like upper(?)");
		query.setParameter(1, "%" + lastName + "%");

		long total = (Long) query.getSingleResult();
		return new Page<PersonDO>(listDO, page, perPage, total);
	}

	@Override
	public Person getPerson(Long id) throws ServiceException {
		return getEntityManager().find(Person.class, id);
	}

}