package cl.votainteligente.legislativo.model.DO;

import java.util.HashSet;
import java.util.Set;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Session;

public class SessionDO {

	private Long id;
	private Long legislature;
	private Long number;
	private Set<Long> discussedBillsId;
	private String date;

	public SessionDO(Session session) {
		this.id = session.getId();
		this.number = session.getNumber();
		this.legislature = session.getLegislature();
		this.date = (session.getDate() != null) ? DOUtil.getDateFormat()
				.format(session.getDate()) : null;
		discussedBillsId = new HashSet<Long>();
		Set<Bill> bills = session.getDiscussedBills();
		for (Bill bill : bills)
			this.discussedBillsId.add(bill.getId());
	}

	public SessionDO() {

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
	 * @return the legislature
	 */
	public Long getLegislature() {
		return legislature;
	}

	/**
	 * @param legislature
	 *            the legislature to set
	 */
	public void setLegislature(Long legislature) {
		this.legislature = legislature;
	}

	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * @return the discussedBillsId
	 */
	public Set<Long> getDiscussedBillsId() {
		return discussedBillsId;
	}

	/**
	 * @param discussedBillsId
	 *            the discussedBillsId to set
	 */
	public void setDiscussedBillsId(Set<Long> discussedBillsId) {
		this.discussedBillsId = discussedBillsId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
