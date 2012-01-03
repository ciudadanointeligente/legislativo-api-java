package cl.votainteligente.legislativo.model.domainobjects;

import java.util.HashSet;
import java.util.Set;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.SessionCommission;
import cl.votainteligente.legislativo.model.Vote;

public class SessionCommissionDetailedDO {

	private Long id;
	private Long number;
	private Long legislature;
	private Set<Long> discussedBillsId;
	private String date;
	private Set<Long> assitantId;
	private Long commissionId;
	private String sessionTableURL;
	private String sessionAccountURL;
	private Set<Long> sessionVotesId;

	public SessionCommissionDetailedDO(SessionCommission session) {
		this.id = session.getId();
		this.number = session.getNumber();
		this.setLegislature(session.getLegislature());
		Commission c = session.getCommission();
		if (c != null)
			this.setCommissionId(c.getId());
		this.date = (session.getDate() != null) ? DOUtil.getDateFormat()
				.format(session.getDate()) : null;
		this.discussedBillsId = new HashSet<Long>();
		Set<Bill> bills = session.getDiscussedBills();
		for (Bill bill : bills)
			this.discussedBillsId.add(bill.getId());
		this.sessionAccountURL = session.getSessionAccountURL();
		this.sessionTableURL = session.getSessionTableURL();
		this.assitantId = new HashSet<Long>();
		Set<Person> assistant = session.getAssitant();
		for (Person person : assistant)
			this.assitantId.add(person.getId());
		this.sessionVotesId = new HashSet<Long>();
		Set<Vote> votes = session.getVotes();
		for (Vote vote : votes)
			this.sessionVotesId.add(vote.getId());
	}

	public SessionCommissionDetailedDO() {

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

	/**
	 * @return the assitantId
	 */
	public Set<Long> getAssitantId() {
		return assitantId;
	}

	/**
	 * @param assitantId
	 *            the assitantId to set
	 */
	public void setAssitantId(Set<Long> assitantId) {
		this.assitantId = assitantId;
	}

	/**
	 * @return the sessionTableURL
	 */
	public String getSessionTableURL() {
		return sessionTableURL;
	}

	/**
	 * @param sessionTableURL
	 *            the sessionTableURL to set
	 */
	public void setSessionTableURL(String sessionTableURL) {
		this.sessionTableURL = sessionTableURL;
	}

	/**
	 * @return the sessionAccountURL
	 */
	public String getSessionAccountURL() {
		return sessionAccountURL;
	}

	/**
	 * @param sessionAccountURL
	 *            the sessionAccountURL to set
	 */
	public void setSessionAccountURL(String sessionAccountURL) {
		this.sessionAccountURL = sessionAccountURL;
	}

	public void setLegislature(Long legislature) {
		this.legislature = legislature;
	}

	public Long getLegislature() {
		return legislature;
	}

	public void setSessionVotesId(Set<Long> sessionVotesId) {
		this.sessionVotesId = sessionVotesId;
	}

	public Set<Long> getSessionVotesId() {
		return sessionVotesId;
	}

	public void setCommissionId(Long CommissionId) {
		this.commissionId = CommissionId;
	}

	public Long getCommissionId() {
		return commissionId;
	}
}
