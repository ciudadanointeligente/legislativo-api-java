package cl.votainteligente.legislativo.model.domainobjects;

import cl.votainteligente.legislativo.model.Party;

public class PartyDO {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PartyDO(Party party) {
		this.id = party.getId();
		this.name = party.getFirstName();
	}

}
