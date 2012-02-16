package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommissionDO extends AgrupationDO {

	private Long chamberId;
	private CommissionType commissionType;

	public CommissionDO() {
		super();
	}

	public CommissionDO(Commission commission) {
		super(commission);
		this.setChamberId(commission.getChamber().getId());
		this.commissionType = commission.getCommissionType();
	}

	public Long getChamberId() {
		return chamberId;
	}

	public void setChamberId(Long chamberId) {
		this.chamberId = chamberId;
	}

	public CommissionType getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(CommissionType commissionType) {
		this.commissionType = commissionType;
	}
}
