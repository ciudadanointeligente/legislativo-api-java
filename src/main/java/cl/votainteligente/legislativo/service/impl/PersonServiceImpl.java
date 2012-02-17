package cl.votainteligente.legislativo.service.impl;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.model.DO.PersonDetailedDO;
import cl.votainteligente.legislativo.service.EntityManagerService;
import cl.votainteligente.legislativo.service.PersonService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

@Service
public class PersonServiceImpl extends EntityManagerService implements PersonService {

	@Override
	public Person newPerson(Person person) throws ServiceException {
		getEntityManager().persist(person);
		return person;
	}

	@Override
	public Page<PersonDO> getAllPersonDOs(int page, int perPage) throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Person p");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> personDOList = new ArrayList<PersonDO>();

		for (Person person : (List<Person>) query.getResultList()) {
			personDOList.add(person.asDomainObject());
		}

		query = getEntityManager().createQuery("select count(p) from Person p");
		long total = (Long) query.getSingleResult();
		return new Page<PersonDO>(personDOList, page, perPage, total);
	}

	@Override
	public Page<PersonDO> findPersonsByFirstName(String firstName, int page, int perPage) {
		Query query = getEntityManager().createQuery("select p from Person p where upper(p.firstName) like upper(?)");
		query.setParameter(1, "%" + firstName + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> personDOList = new ArrayList<PersonDO>();

		for (Person person : (List<Person>) query.getResultList()) {
			personDOList.add(person.asDomainObject());
		}

		query = getEntityManager().createQuery("select count(p) from Person p  where upper(p.firstName) like upper(?)");
		query.setParameter(1, "%" + firstName + "%");
		long total = (Long) query.getSingleResult();
		return new Page<PersonDO>(personDOList, page, perPage, total);
	}

	@Override
	public Page<PersonDO> findPersonsByLastName(String lastName, int page, int perPage) {
		Query query = getEntityManager().createQuery("select p from Person p where upper(p.lastName) like upper(?)");
		query.setParameter(1, "%" + lastName + "%");
		query.setFirstResult((page - 1) * perPage);
		query.setMaxResults(perPage);
		List<PersonDO> personDOList = new ArrayList<PersonDO>();

		for (Person person : (List<Person>) query.getResultList()) {
			personDOList.add(person.asDomainObject());
		}

		query = getEntityManager().createQuery("select count(p) from Person p where upper(p.lastName) like upper(?)");
		query.setParameter(1, "%" + lastName + "%");

		long total = (Long) query.getSingleResult();
		return new Page<PersonDO>(personDOList, page, perPage, total);
	}

	@Override
	public Person getPerson(Long id) throws ServiceException {
		return getEntityManager().find(Person.class, id);
	}

	@Override
	public PersonDetailedDO getPersonDetailedDO(Long id) throws ServiceException {
		return new PersonDetailedDO(getPerson(id));
	}
}