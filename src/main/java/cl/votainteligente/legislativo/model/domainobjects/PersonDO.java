package cl.votainteligente.legislativo.model.domainobjects;

import java.sql.Timestamp;
import cl.votainteligente.legislativo.model.Person;

public class PersonDO {

	private Long id;
	private String firstName;
	private String lastName;
	private String party;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public PersonDO(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.party = person.getParty();
		this.createdAt = person.getCreatedAt();
		this.updatedAt = person.getUpdatedAt();
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
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
}