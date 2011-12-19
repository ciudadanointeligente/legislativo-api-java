package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cl.votainteligente.legislativo.model.domainobjects.PartyDO;

@Entity
@Table(name = "party")
public class Party extends Agrupation {
	@Column
	private String address;

	@Column
	private String history;

	@Column
	private String initials;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column
	private String principles;

	// TODO: validar traduccion real de mesa joven y mesa adulta
	@OneToMany
	@JoinColumn(name = "youth_board_id")
	private Set<Person> youthBoard;

	@OneToMany
	@JoinColumn(name = "adult_board_id")
	private Set<Person> adultBoard;

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

	public String getPrinciples() {
		return principles;
	}

	public void setPrinciples(String principles) {
		this.principles = principles;
	}

	public Set<Person> getYouthBoard() {
		return youthBoard;
	}

	public void setYouthBoard(Set<Person> youthBoard) {
		this.youthBoard = youthBoard;
	}

	public Set<Person> getAdultBoard() {
		return adultBoard;
	}

	public void setAdultBoard(Set<Person> adultBoard) {
		this.adultBoard = adultBoard;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Transient
	public PartyDO asDomainObject() {
		return new PartyDO(this);
	}
}
