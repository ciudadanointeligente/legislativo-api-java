package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.ChamberDO;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

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

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

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

	public Set<Commission> getCommissions() {
		return commissions;
	}

	public void setCommissions(Set<Commission> commissions) {
		this.commissions = commissions;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	public Set<Debate> getDebates() {
		return debates;
	}

	public void setDebates(Set<Debate> debates) {
		this.debates = debates;
	}

	public Set<SessionChamber> getSessions() {
		return sessions;
	}

	public void setSessions(Set<SessionChamber> sessions) {
		this.sessions = sessions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ChamberDO asDomainObject() {
		return new ChamberDO(this);
	}
}
