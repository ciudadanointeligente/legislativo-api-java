package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags", cascade = { CascadeType.MERGE })
	private Set<Debate> debates;

	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Debate> getDebates() {
		return debates;
	}

	public void setDebates(Set<Debate> debates) {
		this.debates = debates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
