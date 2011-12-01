package cl.votainteligente.legislativo.model.domainobjects;

import java.util.Date;

import cl.votainteligente.legislativo.model.Bill;

public class BillDO {

	private Long id;
	private String title;
	private Date entryDate;
	private Date updatedAt;

	public BillDO(Bill bill) {
		this.id = bill.getId();
		this.title = bill.getTitle();
		this.entryDate = bill.getEntryDate();
		this.updatedAt = bill.getUpdatedAt();
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
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
}
