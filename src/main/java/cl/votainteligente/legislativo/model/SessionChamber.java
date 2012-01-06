package cl.votainteligente.legislativo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDetailedDO;

@Entity
@Table(name = "session_chamber")
public class SessionChamber extends Session {

	@ManyToOne
	@JoinColumn(name = "chamber_id")
	private Chamber chamber;

	/**
	 * @return the chamber
	 */
	public Chamber getChamber() {
		return chamber;
	}

	/**
	 * @param chamber
	 *            the chamber to set
	 */
	public void setChamber(Chamber chamber) {
		this.chamber = chamber;
	}

	public SessionChamberDO asSessionChamberDomainObject() {
		return new SessionChamberDO(this);
	}

	public SessionChamberDetailedDO asSessionChamberDetailedDomainObject() {
		return new SessionChamberDetailedDO(this);
	}
}
