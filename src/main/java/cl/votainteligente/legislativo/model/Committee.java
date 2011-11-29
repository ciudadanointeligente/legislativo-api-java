package cl.votainteligente.legislativo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "committee")
public class Committee {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "president_id", nullable = false)
	private Parliamentarian president;

	@ManyToOne
	@JoinColumn(name = "president_substitute_id", nullable = false)
	private Parliamentarian president_substitute;

	@ManyToOne
	@JoinColumn(name = "party_id", nullable = false)
	private Party party;

	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parliamentarian getPresident() {
		return president;
	}

	public void setPresident(Parliamentarian president) {
		this.president = president;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public void setPresident_substitute(Parliamentarian president_substitute) {
		this.president_substitute = president_substitute;
	}

	public Parliamentarian getPresident_substitute() {
		return president_substitute;
	}

}
