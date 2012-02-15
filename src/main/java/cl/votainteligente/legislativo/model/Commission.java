package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.AgrupationDO;
import cl.votainteligente.legislativo.model.DO.CommissionDO;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "commission")
public class Commission extends Agrupation {

	@Column(name = "executive_lawyer")
	private String executiveLawyer;

	@Column(name = "secretary_lawyer")
	private String secretaryLawyer;

	@Column(name = "assistant_lawyer")
	private String assistantLawyer;

	private String form;

	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "commission")
	private Set<SessionCommission> sessions;

	@ManyToOne
	@JoinColumn(name = "chamber_id", nullable = false)
	private Chamber chamber;

	@ManyToOne
	@JoinColumn(name = "commission_type_id", nullable = false)
	private CommissionType commissionType;

	@ManyToMany(mappedBy = "participantCommissions", cascade = { CascadeType.REMOVE })
	private Set<DebateInCommission> debates;

	public String getExecutiveLawyer() {
		return executiveLawyer;
	}

	public void setExecutiveLawyer(String executiveLawyer) {
		this.executiveLawyer = executiveLawyer;
	}

	public String getSecretaryLawyer() {
		return secretaryLawyer;
	}

	public void setSecretaryLawyer(String secretaryLawyer) {
		this.secretaryLawyer = secretaryLawyer;
	}

	public String getAssistantLawyer() {
		return assistantLawyer;
	}

	public void setAssistantLawyer(String assistantLawyer) {
		this.assistantLawyer = assistantLawyer;
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

	public Set<SessionCommission> getSessions() {
		return sessions;
	}

	public void setSessions(Set<SessionCommission> sessions) {
		this.sessions = sessions;
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

	public Set<DebateInCommission> getDebates() {
		return debates;
	}

	public void setDebates(Set<DebateInCommission> debates) {
		this.debates = debates;
	}

	@Transient
	public AgrupationDO asDomainObject() {
		return new CommissionDO(this);
	}
}
