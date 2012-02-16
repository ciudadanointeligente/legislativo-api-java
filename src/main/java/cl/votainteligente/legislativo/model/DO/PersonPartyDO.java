package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonPartyDO {

	private Long id;
	private String firstName;
	private String lastName;
	private Long partyId;
	private String partyName;

	public PersonPartyDO() {
	}

	public PersonPartyDO(Person person, Party party) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.partyId = party.getId();
		this.partyName = party.getName();
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

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
}
