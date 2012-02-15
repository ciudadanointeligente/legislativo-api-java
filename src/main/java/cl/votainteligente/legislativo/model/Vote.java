package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.VoteDO;
import cl.votainteligente.legislativo.model.DO.VoteDetailedDO;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

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

	/* 0: Approved 1: Rejected 2: Draw 3: No quorum */
	@Column(name = "result")
	private Long result;

	@Column(name = "quorum")
	private String quorum;

	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;

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

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public int getYesVotes() {
		return yesVotes;
	}

	public void setYesVotes(int yesVotes) {
		this.yesVotes = yesVotes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<SingleVote> getVotes() {
		return votes;
	}

	public void setVotes(Set<SingleVote> votes) {
		this.votes = votes;
	}

	public int getNoVotes() {
		return noVotes;
	}

	public void setNoVotes(int noVotes) {
		this.noVotes = noVotes;
	}

	public int getAbstentionVotes() {
		return abstentionVotes;
	}

	public void setAbstentionVotes(int abstentionVotes) {
		this.abstentionVotes = abstentionVotes;
	}

	public int getAbsentVotes() {
		return absentVotes;
	}

	public void setAbsentVotes(int absentVotes) {
		this.absentVotes = absentVotes;
	}

	public int getMatchingVotes() {
		return matchingVotes;
	}

	public void setMatchingVotes(int matchingVotes) {
		this.matchingVotes = matchingVotes;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public String getQuorum() {
		return quorum;
	}

	public void setQuorum(String quorum) {
		this.quorum = quorum;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public VoteDO asDomainObject() {
		return new VoteDO(this);
	}

	public VoteDetailedDO asDetailedDomainObject() {
		return new VoteDetailedDO(this);
	}
}
