package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.SingleVote;

public class SingleVoteDetailedDO {

	private Long id;
	private Long personId;
	private Long voteId;
	private String voteDetail;

	public SingleVoteDetailedDO() {
	}

	public SingleVoteDetailedDO(SingleVote singleVote) {
		this.id = singleVote.getId();
		if (singleVote.getPerson() != null) {
			this.personId = singleVote.getPerson().getId();
		}
		if (singleVote.getVote() != null) {
			this.voteId = singleVote.getVote().getId();
		}
		this.voteDetail = singleVote.getVoteDetail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public String getVoteDetail() {
		return voteDetail;
	}

	public void setVoteDetail(String voteDetail) {
		this.voteDetail = voteDetail;
	}
}
