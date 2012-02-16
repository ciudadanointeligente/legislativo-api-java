package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Person;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonDO {

	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthday;

	public PersonDO() {
	}

	public PersonDO(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.gender = person.getGender();
		this.birthday = (person.getBirthday() != null) ? DOUtil.getDateFormat().format(person.getBirthday()) : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
