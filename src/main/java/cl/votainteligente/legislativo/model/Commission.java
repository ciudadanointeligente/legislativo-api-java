package cl.votainteligente.legislativo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commission")
public class Commission extends Agrupation {

	private String executiveLawyer;
	private String secretaryLawyer;
	private String assistantLawyer;
	private String form;
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "chamber_id", nullable = false)
	private Chamber chamber;

	@ManyToOne
	@JoinColumn(name = "commission_type_id", nullable = false)
	private CommissionType commissionType;

	public String getAssistantLawyer() {
		return assistantLawyer;
	}

	public void setAssistantLawyer(String assistantLawyer) {
		this.assistantLawyer = assistantLawyer;
	}

	public String getExecutiveLawyer() {
		return executiveLawyer;
	}

	public void setExecutive_lawyer(String executiveLawyer) {
		this.executiveLawyer = executiveLawyer;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSecretaryLawyer() {
		return secretaryLawyer;
	}

	public void setSecretaryLawyer(String secretaryLawyer) {
		this.secretaryLawyer = secretaryLawyer;
	}

	public Chamber getChamber() {
		return chamber;
	}

	public void setChamber(Chamber chamber) {
		this.chamber = chamber;
	}

	public CommissionType getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(CommissionType commissionType) {
		this.commissionType = commissionType;
	}
}