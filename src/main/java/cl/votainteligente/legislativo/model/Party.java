package cl.votainteligente.legislativo.model;

import java.net.URL;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "party")
public class Party {

	@Id
	@GeneratedValue
	private Long id;

	private String address;
	private String name;
	private String email;
	private String history;
	private String initials;
	private String phone_number;
	private String principles;
	private Date foundation_date;
	private URL web;

	// TODO: validar traduccion real de mesa joven y mesa adulta
	@OneToMany
	@JoinColumn(name = "youth_board_id")
	private Set<Activist> youth_board;

	@OneToMany
	@JoinColumn(name = "adult_board_id")
	private Set<Activist> adult_board;

	private Date created_at;
	private Date updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPrinciples() {
		return principles;
	}

	public void setPrinciples(String principles) {
		this.principles = principles;
	}

	public Date getFoundation_date() {
		return foundation_date;
	}

	public void setFoundation_date(Date foundation_date) {
		this.foundation_date = foundation_date;
	}

	public URL getWeb() {
		return web;
	}

	public void setWeb(URL web) {
		this.web = web;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public void setAdult_board(Set<Activist> adult_board) {
		this.adult_board = adult_board;
	}

	public Set<Activist> getAdult_board() {
		return adult_board;
	}

	public void setYouth_board(Set<Activist> youth_board) {
		this.youth_board = youth_board;
	}

	public Set<Activist> getYouth_board() {
		return youth_board;
	}
}
