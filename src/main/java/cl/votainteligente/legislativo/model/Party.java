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
	private Set<Person> youth_board;

	@OneToMany
	@JoinColumn(name = "adult_board_id")
	private Set<Person> adult_board;

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

	public Set<Person> getYouth_board() {
		return youth_board;
	}

	public void setYouth_board(Set<Person> youth_board) {
		this.youth_board = youth_board;
	}

	public Set<Person> getAdult_board() {
		return adult_board;
	}

	public void setAdult_board(Set<Person> adult_board) {
		this.adult_board = adult_board;
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
