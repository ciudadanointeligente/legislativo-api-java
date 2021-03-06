package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PartyDetailedDO extends AgrupationDetailedDO {

	private String address;
	private String history;
	private String initials;
	private String phoneNumber;
	private String principles;
	private Set<Long> youthBoardId;
	private Set<Long> adultBoardId;

	public PartyDetailedDO() {
		super();
	}

	public PartyDetailedDO(Party party) {
		super(party);
		this.address = party.getAddress();
		this.history = party.getHistory();
		this.initials = party.getInitials();
		this.phoneNumber = party.getPhoneNumber();
		this.principles = party.getPrinciples();
		this.youthBoardId = new HashSet<Long>();
		Set<Person> youthBoard = party.getYouthBoard();
		for (Person p : youthBoard) {
			this.youthBoardId.add(p.getId());
		}
		this.adultBoardId = new HashSet<Long>();
		Set<Person> adultBoard = party.getAdultBoard();
		for (Person p : adultBoard) {
			this.adultBoardId.add(p.getId());
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPrinciples() {
		return principles;
	}

	public void setPrinciples(String principles) {
		this.principles = principles;
	}

	public Set<Long> getYouthBoardId() {
		return youthBoardId;
	}

	public void setYouthBoardId(Set<Long> youthBoardId) {
		this.youthBoardId = youthBoardId;
	}

	public Set<Long> getAdultBoardId() {
		return adultBoardId;
	}

	public void setAdultBoardId(Set<Long> adultBoardId) {
		this.adultBoardId = adultBoardId;
	}
}
