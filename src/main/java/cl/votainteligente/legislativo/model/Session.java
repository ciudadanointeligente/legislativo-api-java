package cl.votainteligente.legislativo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cl.votainteligente.legislativo.model.DO.SessionDO;
import cl.votainteligente.legislativo.model.DO.SessionDetailedDO;

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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public void setAssitant(Set<Person> assitant) {
		this.assitant = assitant;
	}

	public Set<Person> getAssitant() {
		return assitant;
	}

	public void setDiscussedBills(Set<Bill> discussedBills) {
		this.discussedBills = discussedBills;
	}

	public Set<Bill> getDiscussedBills() {
		return discussedBills;
	}

	public void setSessionTableURL(String sessionTableURL) {
		this.sessionTableURL = sessionTableURL;
	}

	public String getSessionTableURL() {
		return sessionTableURL;
	}

	public void setSessionAccountURL(String sessionAccountURL) {
		this.sessionAccountURL = sessionAccountURL;
	}

	public String getSessionAccountURL() {
		return sessionAccountURL;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setLegislature(Long legislature) {
		this.legislature = legislature;
	}

	public Long getLegislature() {
		return legislature;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public SessionDO asDomainObject() {
		return new SessionDO(this);
	}

	public SessionDetailedDO asDetailedDomainObject() {
		return new SessionDetailedDO(this);
	}

	public void addDiscussedBill(Bill bill) {
		if(discussedBills == null)
			this.discussedBills = new HashSet<Bill>();
		this.discussedBills.add(bill);
	}
}
