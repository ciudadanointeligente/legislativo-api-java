package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.SessionChamber;

import java.util.HashSet;
import java.util.Set;

public class SessionChamberDO {

	private Long id;
	private Long legislature;
	private Long number;
	private Set<Long> discussedBillsId;
	private Long chamberId;
	private String date;

	public SessionChamberDO() {
	}

	public SessionChamberDO(SessionChamber session) {
		this.id = session.getId();
		this.number = session.getNumber();
		this.legislature = session.getLegislature();
		Chamber chamber = session.getChamber();
		if (chamber != null) {
			this.chamberId = chamber.getId();
		}
		this.date = (session.getDate() != null) ? DOUtil.getDateFormat().format(session.getDate()) : null;
		discussedBillsId = new HashSet<Long>();
		Set<Bill> bills = session.getDiscussedBills();
		for (Bill bill : bills) {
			this.discussedBillsId.add(bill.getId());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLegislature() {
		return legislature;
	}

	public void setLegislature(Long legislature) {
		this.legislature = legislature;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Set<Long> getDiscussedBillsId() {
		return discussedBillsId;
	}

	public void setDiscussedBillsId(Set<Long> discussedBillsId) {
		this.discussedBillsId = discussedBillsId;
	}

	public Long getChamberId() {
		return chamberId;
	}

	public void setChamberId(Long chamberId) {
		this.chamberId = chamberId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
