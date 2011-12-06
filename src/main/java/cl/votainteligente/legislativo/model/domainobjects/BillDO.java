package cl.votainteligente.legislativo.model.domainobjects;

import cl.votainteligente.legislativo.model.Bill;

public class BillDO {

	private Long id;
	private String title;
	private Long entryDate;
	private Long updatedAt;

	public BillDO(Bill bill) {
		this.id = bill.getId();
		this.title = bill.getTitle();
		this.entryDate = bill.getEntryDate().getTime();
		this.updatedAt = bill.getUpdatedAt().getTime();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the entryDate
	 */
	public Long getEntryDate() {
		return entryDate;
	}

	/**
	 * @return the updatedAt
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}
}
