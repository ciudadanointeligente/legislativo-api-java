package cl.votainteligente.legislativo.model.domainobjects;

import java.util.Set;

import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.Person;

public class PartyDetailedDO {

	private Long id;
	private String name;
	private String address;
	private String history;
	private String initials;
	private String phoneNumber;
	private String principles;
	private Set<Long> youthBoardId;
	private Set<Long> adultBoardId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public PartyDetailedDO(Party party) {
		this.id = party.getId();
		this.name = party.getName();
		this.address = party.getAddress();
		this.history = party.getName();
		this.initials = party.getInitials();
		this.phoneNumber = party.getPhoneNumber();
		this.principles = party.getPrinciples();
		Set<Person> youthBoard = party.getYouthBoard();
		for (Person p : youthBoard)
			youthBoardId.add(p.getId());
		Set<Person> adultBoard = party.getAdultBoard();
		for (Person p : adultBoard)
			adultBoardId.add(p.getId());
	}

}
