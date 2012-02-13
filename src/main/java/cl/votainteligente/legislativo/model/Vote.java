package cl.votainteligente.legislativo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cl.votainteligente.legislativo.model.DO.VoteDO;
import cl.votainteligente.legislativo.model.DO.VoteDetailedDO;

@Entity
@Table(name = "vote")
public class Vote {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "session_id")
	private Session session;

	@Column(name = "time")
	@Temporal(TemporalType.TIME)
	private Date time;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(name = "yes_votes")
	private int yesVotes;

	@Column(name = "type")
	private String type;

	@OneToMany(mappedBy = "vote")
	@Column
	private Set<SingleVote> votes;

	@Column(name = "no_votes")
	private int noVotes;

	@Column(name = "abstention_votes")
	private int abstentionVotes;

	@Column(name = "absent_votes")
	private int absentVotes;

	@Column(name = "matching_votes")
	private int matchingVotes;

	/*
	 * 0 : Approved 1 : Rejected 2 : Draw 3 : No quorum
	 */
	@Column(name = "result")
	private Long result;

	@Column(name = "quorum")
	private String quorum;

	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the yesVotes
	 */
	public int getYesVotes() {
		return yesVotes;
	}

	/**
	 * @param yesVotes
	 *            the yesVotes to set
	 */
	public void setYesVotes(int yesVotes) {
		this.yesVotes = yesVotes;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	/**
	 * @return the noVotes
	 */
	public int getNoVotes() {
		return noVotes;
	}

	/**
	 * @param noVotes
	 *            the noVotes to set
	 */
	public void setNoVotes(int noVotes) {
		this.noVotes = noVotes;
	}

	/**
	 * @return the abstentionVotes
	 */
	public int getAbstentionVotes() {
		return abstentionVotes;
	}

	/**
	 * @param abstentionVotes
	 *            the abstentionVotes to set
	 */
	public void setAbstentionVotes(int abstentionVotes) {
		this.abstentionVotes = abstentionVotes;
	}

	/**
	 * @return the absentVotes
	 */
	public int getAbsentVotes() {
		return absentVotes;
	}

	/**
	 * @param absentVotes
	 *            the absentVotes to set
	 */
	public void setAbsentVotes(int absentVotes) {
		this.absentVotes = absentVotes;
	}

	/**
	 * @return the matchingVotes
	 */
	public int getMatchingVotes() {
		return matchingVotes;
	}

	/**
	 * @param matchingVotes
	 *            the matchingVotes to set
	 */
	public void setMatchingVotes(int matchingVotes) {
		this.matchingVotes = matchingVotes;
	}

	/**
	 * @return the result
	 */
	public Long getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Long result) {
		this.result = result;
	}

	/**
	 * @return the quorum
	 */
	public String getQuorum() {
		return quorum;
	}

	/**
	 * @param quorum
	 *            the quorum to set
	 */
	public void setQuorum(String quorum) {
		this.quorum = quorum;
	}

	/**
	 * @return the bill
	 */
	public Bill getBill() {
		return bill;
	}

	/**
	 * @param bill
	 *            the bill to set
	 */
	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public void setVotes(Set<SingleVote> votes) {
		this.votes = votes;
	}

	public Set<SingleVote> getVotes() {
		return votes;
	}

	public VoteDO asDomainObject() {
		return new VoteDO(this);
	}

	public VoteDetailedDO asDetailedDomainObject() {
		return new VoteDetailedDO(this);
	}

}
