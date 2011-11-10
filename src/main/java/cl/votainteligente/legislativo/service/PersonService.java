package cl.votainteligente.legislativo.service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Person;

import java.util.List;

public interface PersonService {
	List<Person> getAllPersons() throws ServiceException;
	Page<Person> getAllPersons(Integer offset, Integer limit) throws ServiceException;
	Person getPerson(Long personId) throws ServiceException;
	Person savePerson(Person person) throws ServiceException;
	void deletePerson(Person person) throws ServiceException;
}
