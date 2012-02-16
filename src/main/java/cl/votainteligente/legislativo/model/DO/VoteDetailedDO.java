package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

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

	public VoteDetailedDO() {
	}

	public VoteDetailedDO(Vote vote) {
		this.id = vote.getId();
		if (vote.getBill() != null) {
			this.billDetail = vote.getBill().asDomainObject();
		}
		Long voteTotal = vote.getResult();
		if (voteTotal == 0L) {
			this.result = "0: Aprobado";
		} else if (voteTotal == 1L) {
			this.result = "1: Rechazado";
		} else if (voteTotal == 2L) {
			this.result = "2: Empate";
		} else if (voteTotal == 3L) {
			this.result = "3: No quorum";
		}
		if (vote.getSession() != null) {
			this.sessionId = vote.getSession().getId();
		}
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
		for (SingleVote v : votes) {
			voteDetails.add(v.asDomainObject());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BillDO getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(BillDO billDetail) {
		this.billDetail = billDetail;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getYesVotes() {
		return yesVotes;
	}

	public void setYesVotes(int yesVotes) {
		this.yesVotes = yesVotes;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getQuorum() {
		return quorum;
	}

	public void setQuorum(String quorum) {
		this.quorum = quorum;
	}

	public Set<SingleVoteDO> getVoteDetails() {
		return voteDetails;
	}

	public void setVoteDetails(Set<SingleVoteDO> voteDetails) {
		this.voteDetails = voteDetails;
	}
}
