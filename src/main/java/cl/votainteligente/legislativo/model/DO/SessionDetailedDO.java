package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.*;

import java.util.HashSet;
import java.util.Set;

public class SessionDetailedDO {

	private Long id;
	private Long number;
	private Long legislature;
	private Set<Long> discussedBillsId;
	private String date;
	private Set<Long> assitantId;
	private String sessionTableURL;
	private String sessionAccountURL;
	private Set<Long> sessionVotesId;

	public SessionDetailedDO() {
	}

	public SessionDetailedDO(Session session) {
		this.id = session.getId();
		this.number = session.getNumber();
		this.setLegislature(session.getLegislature());
		this.date = (session.getDate() != null) ? DOUtil.getDateFormat().format(session.getDate()) : null;
		this.discussedBillsId = new HashSet<Long>();
		Set<Bill> bills = session.getDiscussedBills();
		for (Bill bill : bills) {
			this.discussedBillsId.add(bill.getId());
		}
		this.sessionAccountURL = session.getSessionAccountURL();
		this.sessionTableURL = session.getSessionTableURL();
		this.assitantId = new HashSet<Long>();
		Set<Person> assistant = session.getAssitant();
		for (Person person : assistant) {
			this.assitantId.add(person.getId());
		}
		this.sessionVotesId = new HashSet<Long>();
		Set<Vote> votes = session.getVotes();
		for (Vote vote : votes) {
			this.sessionVotesId.add(vote.getId());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getLegislature() {
		return legislature;
	}

	public void setLegislature(Long legislature) {
		this.legislature = legislature;
	}

	public Set<Long> getDiscussedBillsId() {
		return discussedBillsId;
	}

	public void setDiscussedBillsId(Set<Long> discussedBillsId) {
		this.discussedBillsId = discussedBillsId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Set<Long> getAssitantId() {
		return assitantId;
	}

	public void setAssitantId(Set<Long> assitantId) {
		this.assitantId = assitantId;
	}

	public String getSessionTableURL() {
		return sessionTableURL;
	}

	public void setSessionTableURL(String sessionTableURL) {
		this.sessionTableURL = sessionTableURL;
	}

	public String getSessionAccountURL() {
		return sessionAccountURL;
	}

	public void setSessionAccountURL(String sessionAccountURL) {
		this.sessionAccountURL = sessionAccountURL;
	}

	public Set<Long> getSessionVotesId() {
		return sessionVotesId;
	}

	public void setSessionVotesId(Set<Long> sessionVotesId) {
		this.sessionVotesId = sessionVotesId;
	}
}
