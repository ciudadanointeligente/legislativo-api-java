package cl.votainteligente.legislativo.model.domainobjects;

import java.util.HashSet;
import java.util.Set;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.SessionChamber;

public class SessionChamberDO {

	private Long id;
	private Long legislature;
	private Long number;
	private Set<Long> discussedBillsId;
	private Long chamberId;
	private String date;

	public SessionChamberDO(SessionChamber session) {
		this.id = session.getId();
		this.number = session.getNumber();
		this.legislature = session.getLegislature();
		Chamber c = session.getChamber();
		if (c != null)
			this.chamberId = c.getId();
		this.date = (session.getDate() != null) ? DOUtil.getDateFormat()
				.format(session.getDate()) : null;
		discussedBillsId = new HashSet<Long>();
		Set<Bill> bills = session.getDiscussedBills();
		for (Bill bill : bills)
			this.discussedBillsId.add(bill.getId());
	}

	public SessionChamberDO() {

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
	 * @return the chamberId
	 */
	public Long getChamberId() {
		return chamberId;
	}

	/**
	 * @param chamberId
	 *            the chamberId to set
	 */
	public void setChamberId(Long chamberId) {
		this.chamberId = chamberId;
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

	public void setLegislature(Long legislature) {
		this.legislature = legislature;
	}

	public Long getLegislature() {
		return legislature;
	}

}
