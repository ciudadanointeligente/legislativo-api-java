package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.*;

import org.hibernate.annotations.Type;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "party")
public class Party extends Agrupation {

	@Column
	private String address;

	@Column
	@Type(type = "text")
	private String history;

	@Column
	private String initials;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column
	private String principles;

	@OneToMany
	@JoinColumn(name = "youth_board_id")
	private Set<Person> youthBoard;

	@OneToMany
	@JoinColumn(name = "adult_board_id")
	private Set<Person> adultBoard;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coalition", cascade = { CascadeType.REMOVE })
	private Set<CoalitionAffiliation> coalitionAffiliations;

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

	public Set<CoalitionAffiliation> getCoalitionAffiliations() {
		return coalitionAffiliations;
	}

	public void setCoalitionAffiliations(Set<CoalitionAffiliation> coalitionAffiliations) {
		this.coalitionAffiliations = coalitionAffiliations;
	}

	@Transient
	public AgrupationDO asDomainObject() {
		return new PartyDO(this);
	}

	@Transient
	public AgrupationDetailedDO asDetailedDomainObject() {
		return new PartyDetailedDO(this);
	}
}
