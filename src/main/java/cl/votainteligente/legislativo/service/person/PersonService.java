package cl.votainteligente.legislativo.service.person;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

public interface PersonService {
	Person newPerson(Person person) throws ServiceException;

	Page<PersonDO> getAllPersonDOs(int page, int perPage)
			throws ServiceException;

	Page<PersonDO> findPersonsByFirstName(String firstName, int page,
			int perPage) throws ServiceException;

	Page<PersonDO> findPersonsByLastName(String lastName, int page, int perPage)
			throws ServiceException;

	Person getPerson(Long id) throws ServiceException;
}