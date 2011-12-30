package cl.votainteligente.legislativo.model.domainobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.model.LegislatorRole;

@XmlRootElement
public class CommissionDetailedDO {
	private Long commissionId;
	private Chamber chamber;
	private CommissionType commissionType;
	private String name;
	private List<Long> membersId;

	public CommissionDetailedDO() {

	}

	public CommissionDetailedDO(Commission commission) {
		this.commissionId = commission.getId();
		this.chamber = commission.getChamber();
		this.commissionType = commission.getCommissionType();
		this.name = commission.getName();
		this.membersId = new ArrayList<Long>();
		Set<LegislatorRole> members = commission.getMembers();
		for (LegislatorRole legislatorRole : members) {
			this.membersId.add(legislatorRole.getId());
		}
	}

	/**
	 * @return the commissionId
	 */
	public Long getCommissionId() {
		return commissionId;
	}

	/**
	 * @param commissionId
	 *            the commissionId to set
	 */
	public void setCommissionId(Long commissionId) {
		this.commissionId = commissionId;
	}

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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getMembersId() {
		return this.membersId;
	}

	public void setMembersId(List<Long> membersId) {
		this.membersId = membersId;
	}
}
