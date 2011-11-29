package cl.votainteligente.legislativo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commission")
public class Commission {
	@Id
	@GeneratedValue
	@Column
	private Long id;

	private String assistant_lawyer;
	private Date created_at;
	private String email;
	private String executive_lawyer;
	private String form;
	private String name;
	private String phone_number;
	private String secretary_lawyer;
	private Date updated_at;

	@ManyToOne
	@JoinColumn(name = "chamber_id", nullable = false)
	private Chamber chamber;

	@ManyToOne
	@JoinColumn(name = "commission_type_id", nullable = false)
	private CommissionType commission_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssistant_lawyer() {
		return assistant_lawyer;
	}

	public void setAssistant_lawyer(String assistant_lawyer) {
		this.assistant_lawyer = assistant_lawyer;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
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