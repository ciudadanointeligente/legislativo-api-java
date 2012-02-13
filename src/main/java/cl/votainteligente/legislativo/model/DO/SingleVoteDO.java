package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.SingleVote;

public class SingleVoteDO {

	private Long personId;
	private String voteDetail;

	public SingleVoteDO(SingleVote singleVote) {
		if (singleVote.getPerson() != null)
			this.personId = singleVote.getPerson().getId();
		this.voteDetail = singleVote.getVoteDetail();
	}

	public SingleVoteDO() {

	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the voteDetail
	 */
	public String getVoteDetail() {
		return voteDetail;
	}

	/**
	 * @param voteDetail
	 *            the voteDetail to set
	 */
	public void setVoteDetail(String voteDetail) {
		this.voteDetail = voteDetail;
	}
}
