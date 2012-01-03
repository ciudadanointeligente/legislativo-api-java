package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chamber")
	private Set<Commission> commissions;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "originChamber")
	private Set<Bill> bills;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chamber")
	private Set<Debate> debates;

	@OneToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "chamber")
	private Set<SessionChamber> sessions;

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

	public void setCommissions(Set<Commission> commissions) {
		this.commissions = commissions;
	}

	public Set<Commission> getCommissions() {
		return commissions;
	}

	public void setDebates(Set<Debate> debates) {
		this.debates = debates;
	}

	public Set<Debate> getDebates() {
		return debates;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	/**
	 * @return the sessions
	 */
	public Set<SessionChamber> getSessions() {
		return sessions;
	}

	/**
	 * @param sessions
	 *            the sessions to set
	 */
	public void setSessions(Set<SessionChamber> sessions) {
		this.sessions = sessions;
	}
}
