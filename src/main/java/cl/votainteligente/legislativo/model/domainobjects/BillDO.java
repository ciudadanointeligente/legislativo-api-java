package cl.votainteligente.legislativo.model.domainobjects;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Bill;

@XmlRootElement
public class BillDO {

	private Long id;
	private String bulletinNumber;
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
		this.bulletinNumber = bill.getBulletinNumber();
	}

	public Long getId() {
		return id;
	}

	public String getBulletinNumber() {
		return bulletinNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}
}
