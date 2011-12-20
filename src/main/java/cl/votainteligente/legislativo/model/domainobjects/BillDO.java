package cl.votainteligente.legislativo.model.domainobjects;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Bill;

@XmlRootElement
public class BillDO {

	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	private String title;
	private String entryDate;
	private String updatedAt;

	public BillDO() {
	}

	public BillDO(Bill bill) {
		this.id = bill.getId();
		this.title = bill.getTitle();
		this.entryDate = DOUtil.getDateFormat().format(bill.getEntryDate());
		this.updatedAt = DOUtil.getDateFormat().format(bill.getUpdatedAt());
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
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}
}
