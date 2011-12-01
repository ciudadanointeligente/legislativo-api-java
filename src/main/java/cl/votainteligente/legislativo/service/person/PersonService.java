package cl.votainteligente.legislativo.service.person;

import java.util.List;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

public interface PersonService {
	Person newPerson(Person person) throws ServiceException;

	List<PersonDO> getAllPersonDOs() throws ServiceException;
}