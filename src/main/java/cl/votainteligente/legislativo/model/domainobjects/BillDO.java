package cl.votainteligente.legislativo.model.domainobjects;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.model.Bill;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class BillDO {

	private Long id;
	private String title;
	private String entryDate;
	private String updatedAt;

	public BillDO(Bill bill) {
		this.id = bill.getId();
		this.title = bill.getTitle();
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationProperties.getProperty("do.date.format"),
				new Locale(ApplicationProperties.getProperty("localization.language"),ApplicationProperties.getProperty("localization.country")));
		this.entryDate = dateFormat.format(bill.getEntryDate());
		this.updatedAt = dateFormat.format(bill.getUpdatedAt());
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
