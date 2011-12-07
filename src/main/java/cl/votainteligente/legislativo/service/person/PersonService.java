package cl.votainteligente.legislativo.service.person;

import java.util.List;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

public interface PersonService {
	Person newPerson(Person person) throws ServiceException;

	List<PersonDO> getKPersonDOs(int page, int perPage) throws ServiceException;

	List<PersonDO> getAllPersonDOs() throws ServiceException;

	List<PersonDO> findPersonsByFirstName(String firstName) throws ServiceException;

	List<PersonDO> findPersonsByLastName(String lastName) throws ServiceException;

	Person getPerson(Long id) throws ServiceException;
}