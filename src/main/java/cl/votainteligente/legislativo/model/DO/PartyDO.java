package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Party;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PartyDO extends AgrupationDO {

	public PartyDO() {
		super();
	}

	public PartyDO(Party party) {
		super(party);
	}
}
