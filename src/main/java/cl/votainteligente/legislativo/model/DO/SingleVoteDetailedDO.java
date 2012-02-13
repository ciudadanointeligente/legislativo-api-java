package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.SingleVote;

public class SingleVoteDetailedDO {

	private Long id;
	private Long personId;
	private Long voteId;
	private String voteDetail;

	public SingleVoteDetailedDO(SingleVote singleVote) {
		this.id = singleVote.getId();
		if (singleVote.getPerson() != null)
			this.personId = singleVote.getPerson().getId();
		if (singleVote.getVote() != null)
			this.voteId = singleVote.getVote().getId();
		this.voteDetail = singleVote.getVoteDetail();
	}

	public SingleVoteDetailedDO() {

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
	 * @return the voteId
	 */
	public Long getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId
	 *            the voteId to set
	 */
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
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
