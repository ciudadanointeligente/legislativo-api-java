package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.SingleVote;

public class SingleVoteDO {

	private Long personId;
	private String voteDetail;

	public SingleVoteDO() {
	}

	public SingleVoteDO(SingleVote singleVote) {
		if (singleVote.getPerson() != null) {
			this.personId = singleVote.getPerson().getId();
		}
		this.voteDetail = singleVote.getVoteDetail();
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getVoteDetail() {
		return voteDetail;
	}

	public void setVoteDetail(String voteDetail) {
		this.voteDetail = voteDetail;
	}
}
