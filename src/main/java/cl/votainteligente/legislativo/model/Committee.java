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
	private Legislator president;

	@ManyToOne
	@JoinColumn(name = "president_substitute_id", nullable = false)
	private Legislator president_substitute;

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

	public Legislator getPresident() {
		return president;
	}

	public void setPresident(Legislator president) {
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

	public void setPresident_substitute(Legislator president_substitute) {
		this.president_substitute = president_substitute;
	}

	public Legislator getPresident_substitute() {
		return president_substitute;
	}
}
