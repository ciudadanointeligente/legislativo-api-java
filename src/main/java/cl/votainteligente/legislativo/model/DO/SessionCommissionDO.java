package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.SessionCommission;

import java.util.HashSet;
import java.util.Set;

public class SessionCommissionDO {

	private Long id;
	private Long legislature;
	private Long number;
	private Set<Long> discussedBillsId;
	private Long commissionId;
	private String date;

	public SessionCommissionDO() {
	}

	public SessionCommissionDO(SessionCommission session) {
		this.id = session.getId();
		this.number = session.getNumber();
		this.legislature = session.getLegislature();
		Commission commission = session.getCommission();
		if (commission != null) {
			this.commissionId = commission.getId();
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

	public Long getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(Long commissionId) {
		this.commissionId = commissionId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
