package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Vote;

public class VoteDO {

	private Long id;
	private Long billId;
	private String result;
	private int yesVotes;
	private int noVotes;
	private int abstentionVotes;
	private int absentVotes;
	private int matchingVotes;

	// private String resultInfo;

	public VoteDO() {
	}

	public VoteDO(Vote vote) {
		this.id = vote.getId();
		if (vote.getBill() != null) {
			this.billId = vote.getBill().getId();
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
		this.yesVotes = vote.getYesVotes();
		this.noVotes = vote.getNoVotes();
		this.absentVotes = vote.getAbsentVotes();
		this.abstentionVotes = vote.getAbstentionVotes();
		this.matchingVotes = vote.getMatchingVotes();
		// this.setResultInfo("The result param may have the following values "
		// + "{0:aproved, 1:rejected, 2:draw, 3: no quorum}");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	// public void setResultInfo(String resultInfo) {
	// this.resultInfo = resultInfo;
	// }
	//
	// public String getResultInfo() {
	// return resultInfo;
	// }
}
