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
@Table(name = "coalition_affiliation")
public class CoalitionAffiliation {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private Date admission_date;

	@Column
	private Date departure_date;

	@ManyToOne
	@JoinColumn(name = "political_party_id", nullable = false)
	private Party political_party;

	@ManyToOne
	@JoinColumn(name = "coalition_id", nullable = false)
	private Coalition coalition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAdmission_date() {
		return admission_date;
	}

	public void setAdmission_date(Date admission_date) {
		this.admission_date = admission_date;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public Party getPolitical_party() {
		return political_party;
	}

	public void setPolitical_party(Party political_party) {
		this.political_party = political_party;
	}

	public Coalition getCoalition() {
		return coalition;
	}

	public void setCoalition(Coalition coalition) {
		this.coalition = coalition;
	}

}
