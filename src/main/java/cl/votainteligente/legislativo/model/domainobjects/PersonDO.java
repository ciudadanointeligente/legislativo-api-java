package cl.votainteligente.legislativo.model.domainobjects;

import java.util.Date;

import cl.votainteligente.legislativo.model.Person;

public class PersonDO {

	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public PersonDO(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.gender = person.getGender();
		this.birthday = DOUtil.getDateFormat().format(person.getBirthday());
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
}