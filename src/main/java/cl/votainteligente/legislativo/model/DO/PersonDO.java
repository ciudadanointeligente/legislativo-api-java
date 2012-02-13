package cl.votainteligente.legislativo.model.DO;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Person;

@XmlRootElement
public class PersonDO {

	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public PersonDO() {

	}

	public PersonDO(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.gender = person.getGender();
		this.birthday = (person.getBirthday() != null) ? DOUtil.getDateFormat()
				.format(person.getBirthday()) : null;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the createdAt
	 */

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}