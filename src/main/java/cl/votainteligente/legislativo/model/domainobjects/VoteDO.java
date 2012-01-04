package cl.votainteligente.legislativo.model.domainobjects;

import cl.votainteligente.legislativo.model.Vote;

public class VoteDO {

	private Long id;
	private Long billId;
	private Long result;

	public VoteDO(Vote vote) {
		this.id = vote.getId();
		if (vote.getBill() != null)
			this.billId = vote.getBill().getId();
		this.result = vote.getResult();
	}

	public VoteDO() {

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
	 * @return the billId
	 */
	public Long getBillId() {
		return billId;
	}

	/**
	 * @param billId
	 *            the billId to set
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
	 * @param result
	 *            the result to set
	 */
	public void setResult(Long result) {
		this.result = result;
	}

}
