package cl.votainteligente.legislativo.model.DO;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;

@XmlRootElement
public class CommissionDetailedDO extends AgrupationDetailedDO {
	private Long chamber;
	private CommissionType commissionType;

	public CommissionDetailedDO() {
		super();
	}

	public CommissionDetailedDO(Commission commission) {
		super(commission);
		this.chamber = commission.getChamber().getId();
		this.commissionType = commission.getCommissionType();
	}

	public Long getChamber() {
		return chamber;
	}

	public void setChamber(Long chamber) {
		this.chamber = chamber;
	}

	public CommissionType getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(CommissionType commissionType) {
		this.commissionType = commissionType;
	}

}
