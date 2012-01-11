package cl.votainteligente.legislativo.model.domainobjects;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;

public class VoteDetailedDO {

	private Long id;
	private BillDO billDetail;
	private String result;
	private Long sessionId;
	private Time time;
	private String name;
	private String updatedAt;
	private int yesVotes;
	private int noVotes;
	private int abstentionVotes;
	private String type;
	private int absentVotes;
	private int matchingVotes;
	private String quorum;
	private Set<SingleVoteDO> voteDetails;

	public VoteDetailedDO(Vote vote) {
		this.id = vote.getId();
		if (vote.getBill() != null)
			this.billDetail = vote.getBill().asDomainObject();
		Long resultInt = vote.getResult();
		if (resultInt == 0L)
			this.result = "0: Aprobado";
		else if (resultInt == 1L)
			this.result = "1: Rechazado";
		else if (resultInt == 2L)
			this.result = "2: Empate";
		else if (resultInt == 3L)
			this.result = "3: No quorum";
		if (vote.getSession() != null)
			this.sessionId = vote.getSession().getId();
		this.updatedAt = DOUtil.getDateFormat().format(vote.getUpdatedAt());
		this.yesVotes = vote.getYesVotes();
		this.setType(vote.getType());
		this.noVotes = vote.getNoVotes();
		this.setName(vote.getName());
		this.absentVotes = vote.getAbsentVotes();
		this.abstentionVotes = vote.getAbstentionVotes();
		this.quorum = vote.getQuorum();
		this.matchingVotes = vote.getMatchingVotes();
		this.voteDetails = new HashSet<SingleVoteDO>();
		Set<SingleVote> votes = vote.getVotes();
		for (SingleVote v : votes)
			voteDetails.add(v.asDomainObject());
	}

	public VoteDetailedDO() {

	}

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
	 * @return the billDetail
	 */
	public BillDO getBillDetail() {
		return billDetail;
	}

	/**
	 * @param billDetail
	 *            the billDetail to set
	 */
	public void setBillDetail(BillDO billDetail) {
		this.billDetail = billDetail;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Time getTime() {
		return time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(String updatedAt) {
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

	/**
	 * @return the voteDetails
	 */
	public Set<SingleVoteDO> getVoteDetails() {
		return voteDetails;
	}

	/**
	 * @param voteDetails
	 *            the voteDetails to set
	 */
	public void setVoteDetails(Set<SingleVoteDO> voteDetails) {
		this.voteDetails = voteDetails;
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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
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

}
