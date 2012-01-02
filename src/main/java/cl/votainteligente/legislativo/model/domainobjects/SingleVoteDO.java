package cl.votainteligente.legislativo.model.domainobjects;

import cl.votainteligente.legislativo.model.SingleVote;

public class SingleVoteDO {

	private Long id;
	private Long legislatorRoleId;
	private Long voteId;
	private String voteDetail;

	public SingleVoteDO(SingleVote singleVote) {
		this.id = singleVote.getId();
		if (singleVote.getLegislatorRole() != null)
			this.legislatorRoleId = singleVote.getLegislatorRole().getId();
		if (singleVote.getVote() != null)
			this.voteId = singleVote.getVote().getId();
		this.voteDetail = singleVote.getVoteDetail();
	}

	public SingleVoteDO() {

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
	 * @return the legislatorRoleId
	 */
	public Long getLegislatorRoleId() {
		return legislatorRoleId;
	}

	/**
	 * @param legislatorRoleId
	 *            the legislatorRoleId to set
	 */
	public void setLegislatorRoleId(Long legislatorRoleId) {
		this.legislatorRoleId = legislatorRoleId;
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
