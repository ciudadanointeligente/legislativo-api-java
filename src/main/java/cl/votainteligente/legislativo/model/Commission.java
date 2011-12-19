package cl.votainteligente.legislativo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commission")
public class Commission extends Agrupation {

	private String executive_lawyer;
	private String secretary_lawyer;
	private String assistant_lawyer;
	private String form;
	private String phone_number;

	@ManyToOne
	@JoinColumn(name = "chamber_id", nullable = false)
	private Chamber chamber;

	@ManyToOne
	@JoinColumn(name = "commission_type_id", nullable = false)
	private CommissionType commission_type;

	public String getAssistant_lawyer() {
		return assistant_lawyer;
	}

	public void setAssistant_lawyer(String assistant_lawyer) {
		this.assistant_lawyer = assistant_lawyer;
	}

	public String getExecutive_lawyer() {
		return executive_lawyer;
	}

	public void setExecutive_lawyer(String executive_lawyer) {
		this.executive_lawyer = executive_lawyer;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getSecretary_lawyer() {
		return secretary_lawyer;
	}

	public void setSecretary_lawyer(String secretary_lawyer) {
		this.secretary_lawyer = secretary_lawyer;
	}

	public Chamber getChamber() {
		return chamber;
	}

	public void setChamber(Chamber chamber) {
		this.chamber = chamber;
	}

	public CommissionType getCommission_type() {
		return commission_type;
	}

	public void setCommission_type(CommissionType commission_type) {
		this.commission_type = commission_type;
	}
}