package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chamber")
public class Chamber {
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String name;

	@OneToOne
	@JoinColumn(name = "president_id")
	private Legislator president;

	@OneToOne
	@JoinColumn(name = "first_vice_president_id", nullable = true)
	private Legislator firstVicePresident;

	@OneToOne
	@JoinColumn(name = "second_vice_president_id")
	private Legislator secondVicePresident;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Legislator getPresident() {
		return president;
	}

	public void setPresident(Legislator president) {
		this.president = president;
	}

	public Legislator getFirstVicePresident() {
		return firstVicePresident;
	}

	public void setFirstVicePresident(Legislator firstVicePresident) {
		this.firstVicePresident = firstVicePresident;
	}

	public Legislator getSecondVicePresident() {
		return secondVicePresident;
	}

	public void setSecondVicePresident(Legislator secondVicePresident) {
		this.secondVicePresident = secondVicePresident;
	}
}
