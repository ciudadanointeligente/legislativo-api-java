package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.SessionCommissionDO;
import cl.votainteligente.legislativo.model.DO.SessionCommissionDetailedDO;

import javax.persistence.*;

@Entity
@Table(name = "session_commission")
public class SessionCommission extends Session {

	@ManyToOne
	@JoinColumn(name = "commission_id")
	private Commission commission;

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

	public SessionCommissionDO asSessionCommissionDomainObject() {
		return new SessionCommissionDO(this);
	}

	public SessionCommissionDetailedDO asSessionCommissionDetailedDomainObject() {
		return new SessionCommissionDetailedDO(this);
	}
}
