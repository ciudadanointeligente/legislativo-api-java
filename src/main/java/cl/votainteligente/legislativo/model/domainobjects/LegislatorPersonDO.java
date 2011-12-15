package cl.votainteligente.legislativo.model.domainobjects;

import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;

public class LegislatorPersonDO {

	private Long id;
	private String firstName;
	private String lastName;
	private Long partyId;
	private String partyName;

	public LegislatorPersonDO(Person person, Party party) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.partyId = party.getId();
		this.partyName = party.getName();
	}

	public Long getId() {
		return id;
	}

	public Long getPartyId() {
		return partyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
}