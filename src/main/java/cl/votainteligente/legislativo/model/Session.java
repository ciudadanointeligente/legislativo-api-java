package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.SessionDO;
import cl.votainteligente.legislativo.model.DO.SessionDetailedDO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "session")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column
	private Long id;

	@Column
	private Long number;

	@Column
	private Long legislature;

	@ManyToMany
	private Set<Person> assitant;

	@ManyToMany
	private Set<Bill> discussedBills;

	@Column
	private String sessionTableURL;

	@Column
	private String sessionAccountURL;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	@OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private Set<Vote> votes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getLegislature() {
		return legislature;
	}

	public void setLegislature(Long legislature) {
		this.legislature = legislature;
	}

	public Set<Person> getAssitant() {
		return assitant;
	}

	public void setAssitant(Set<Person> assitant) {
		this.assitant = assitant;
	}

	public Set<Bill> getDiscussedBills() {
		return discussedBills;
	}

	public void setDiscussedBills(Set<Bill> discussedBills) {
		this.discussedBills = discussedBills;
	}

	public String getSessionTableURL() {
		return sessionTableURL;
	}

	public void setSessionTableURL(String sessionTableURL) {
		this.sessionTableURL = sessionTableURL;
	}

	public String getSessionAccountURL() {
		return sessionAccountURL;
	}

	public void setSessionAccountURL(String sessionAccountURL) {
		this.sessionAccountURL = sessionAccountURL;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public SessionDO asDomainObject() {
		return new SessionDO(this);
	}

	public SessionDetailedDO asDetailedDomainObject() {
		return new SessionDetailedDO(this);
	}

	public void addDiscussedBill(Bill bill) {
		if (discussedBills == null)
			this.discussedBills = new HashSet<Bill>();
		this.discussedBills.add(bill);
	}
}
