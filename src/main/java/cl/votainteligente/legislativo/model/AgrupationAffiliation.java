package cl.votainteligente.legislativo.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "agrupation_affiliation")
public class AgrupationAffiliation {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@ManyToOne
	@JoinColumn(name = "agrupation_id", nullable = false)
	private Agrupation agrupation;

	@Column(name = "admission_date")
	private Date admissionDate;

	@Column(name = "departure_date")
	private Date departureDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Agrupation getAgrupation() {
		return agrupation;
	}

	public void setAgrupation(Agrupation agrupation) {
		this.agrupation = agrupation;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
}
