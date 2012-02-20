package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.SessionChamberDO;
import cl.votainteligente.legislativo.model.DO.SessionChamberDetailedDO;

import javax.persistence.*;

@Entity
@Table(name = "session_chamber")
public class SessionChamber extends Session {

	@ManyToOne
	@JoinColumn(name = "chamber_id")
	private Chamber chamber;

	public Chamber getChamber() {
		return chamber;
	}

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
