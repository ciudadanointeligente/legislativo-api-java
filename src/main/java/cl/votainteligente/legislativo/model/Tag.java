package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private Set<Debate> debates;

	@Column
	private String name;

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

	public void setDebates(Set<Debate> debates) {
		this.debates = debates;
	}

	public Set<Debate> getDebates() {
		return debates;
	}

	/*
	 * public void setDebates(Set<Debate> debates) { this.debates = debates; }
	 *
	 * public Set<Debate> getDebates() { return debates; }//
	 */
}
