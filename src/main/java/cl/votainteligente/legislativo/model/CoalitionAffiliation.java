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
	private Date admissionDate;

	@Column
	private Date departureDate;

	@ManyToOne
	@JoinColumn(name = "political_party_id", nullable = false)
	private Party politicalParty;

	@ManyToOne
	@JoinColumn(name = "coalition_id", nullable = false)
	private Coalition coalition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getDeparture_date() {
		return departureDate;
	}

	public void setDeparture_date(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Party getPoliticalParty() {
		return politicalParty;
	}

	public void setPoliticalParty(Party politicalParty) {
		this.politicalParty = politicalParty;
	}

	public Coalition getCoalition() {
		return coalition;
	}

	public void setCoalition(Coalition coalition) {
		this.coalition = coalition;
	}
}
