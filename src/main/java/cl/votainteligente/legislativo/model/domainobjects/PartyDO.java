package cl.votainteligente.legislativo.model.domainobjects;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Party;

@XmlRootElement
public class PartyDO extends AgrupationDO {
	public PartyDO() {
		super();
	}

	public PartyDO(Party party) {
		super(party);
	}
}
