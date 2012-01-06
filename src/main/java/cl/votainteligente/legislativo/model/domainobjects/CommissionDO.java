package cl.votainteligente.legislativo.model.domainobjects;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;

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

	/**
	 * @return the commissionType
	 */
	public CommissionType getCommissionType() {
		return commissionType;
	}

	/**
	 * @param commissionType
	 *            the commissionType to set
	 */
	public void setCommissionType(CommissionType commissionType) {
		this.commissionType = commissionType;
	}

	public void setChamberId(Long chamberId) {
		this.chamberId = chamberId;
	}

	public Long getChamberId() {
		return chamberId;
	}

}
