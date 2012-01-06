package cl.votainteligente.legislativo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDetailedDO;

@Entity
@Table(name = "session_commission")
public class SessionCommission extends Session {

	@ManyToOne
	@JoinColumn(name = "commission_id")
	private Commission commission;

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

	public Commission getCommission() {
		return commission;
	}

	public SessionCommissionDO asSessionCommissionDomainObject(){
		return new SessionCommissionDO(this);
	}

	public SessionCommissionDetailedDO asSessionCommissionDetailedDomainObject() {
		return new SessionCommissionDetailedDO(this);
	}
}
