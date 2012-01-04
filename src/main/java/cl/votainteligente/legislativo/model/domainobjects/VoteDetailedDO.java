package cl.votainteligente.legislativo.model.domainobjects;

import java.util.HashSet;
import java.util.Set;

import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;

public class VoteDetailedDO {

	private Long id;
	private Long billId;
	private Long result;
	private Long sessionId;
	private String updatedAt;
	private int yesVotes;
	private Set<Long> votesId;
	private int noVotes;
	private int abstentionVotes;
	private int absentVotes;
	private int matchingVotes;
	private String quorum;

	public VoteDetailedDO(Vote vote) {
		this.id = vote.getId();
		if (vote.getBill() != null)
			this.billId = vote.getBill().getId();
		this.result = vote.getResult();
		if (vote.getSession() != null)
			this.sessionId = vote.getSession().getId();
		this.updatedAt = DOUtil.getDateFormat().format(vote.getUpdatedAt());
		this.yesVotes = vote.getYesVotes();
		this.noVotes = vote.getNoVotes();
		this.absentVotes = vote.getAbsentVotes();
		this.abstentionVotes = vote.getAbstentionVotes();
		this.quorum = vote.getQuorum();
		this.matchingVotes = vote.getMatchingVotes();
		this.votesId = new HashSet<Long>();
		Set<SingleVote> votes = vote.getVotes();
		for(SingleVote v : votes)
			votesId.add(v.getId());
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the billId
	 */
	public Long getBillId() {
		return billId;
	}

	/**
	 * @param billId the billId to set
	 */
	public void setBillId(Long billId) {
		this.billId = billId;
	}

	/**
	 * @return the result
	 */
	public Long getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Long result) {
		this.result = result;
	}

	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
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
	 * @param yesVotes the yesVotes to set
	 */
	public void setYesVotes(int yesVotes) {
		this.yesVotes = yesVotes;
	}

	/**
	 * @return the votesId
	 */
	public Set<Long> getVotesId() {
		return votesId;
	}

	/**
	 * @param votesId the votesId to set
	 */
	public void setVotesId(Set<Long> votesId) {
		this.votesId = votesId;
	}

	/**
	 * @return the noVotes
	 */
	public int getNoVotes() {
		return noVotes;
	}

	/**
	 * @param noVotes the noVotes to set
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
	 * @param abstentionVotes the abstentionVotes to set
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
	 * @param absentVotes the absentVotes to set
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
	 * @param matchingVotes the matchingVotes to set
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
	 * @param quorum the quorum to set
	 */
	public void setQuorum(String quorum) {
		this.quorum = quorum;
	}

}
