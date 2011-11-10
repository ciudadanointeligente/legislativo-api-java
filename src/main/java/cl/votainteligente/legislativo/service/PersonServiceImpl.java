package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Person;

import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Person> getAllPersons() throws ServiceException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.addOrder(Order.asc("name"));
			List<Person> persons = criteria.list();
			return persons;
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public Page<Person> getAllPersons(Integer offset, Integer limit) throws ServiceException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Long totalResults = (Long) session.createQuery("select count(r) from Person r").uniqueResult();
			Query query = session.createQuery("select r from Person r order by r.first_name asc");
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Person> persons = query.list();

			Page<Person> page = new Page<Person>();
			page.setPage((long) (offset / limit + 1));
			page.setTotalPages((long) Math.ceil(totalResults / (limit * 1.0)));
			page.setTotalResults(totalResults);
			page.setElements(persons);

			return page;
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public Person getPerson(Long personId) throws ServiceException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Person person = (Person) session.get(Person.class, personId);
			return person;
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public Person savePerson(Person person) throws ServiceException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(person);
			return person;
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void deletePerson(Person person) throws ServiceException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(person);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
	}
}
