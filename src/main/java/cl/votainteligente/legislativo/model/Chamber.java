package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cl.votainteligente.legislativo.model.domainobjects.ChamberDO;

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
	private LegislatorRole president;

	@OneToOne
	@JoinColumn(name = "first_vice_president_id")
	private LegislatorRole firstVicePresident;

	@OneToOne
	@JoinColumn(name = "second_vice_president_id")
	private LegislatorRole secondVicePresident;

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

	public LegislatorRole getPresident() {
		return president;
	}

	public void setPresident(LegislatorRole president) {
		this.president = president;
	}

	public LegislatorRole getFirstVicePresident() {
		return firstVicePresident;
	}

	public void setFirstVicePresident(LegislatorRole firstVicePresident) {
		this.firstVicePresident = firstVicePresident;
	}

	public LegislatorRole getSecondVicePresident() {
		return secondVicePresident;
	}

	public void setSecondVicePresident(LegislatorRole secondVicePresident) {
		this.secondVicePresident = secondVicePresident;
	}

	public ChamberDO asDomainObject() {
		return new ChamberDO(this);
	}
}
