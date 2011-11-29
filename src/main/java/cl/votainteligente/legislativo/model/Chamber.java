package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chamber")
public class Chamber {
	@Id
	@GeneratedValue
	@Column
	private Long id;

	private String name;

	@OneToOne
	@JoinColumn(name = "president_id", nullable = false)
	private Parliamentarian president;

	@OneToOne
	@JoinColumn(name = "first_vice_president_id", nullable = false)
	private Parliamentarian first_vice_president;

	@OneToOne
	@JoinColumn(name = "second_vice_president_id", nullable = true)
	private Parliamentarian second_vice_president;

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

	public Parliamentarian getPresident() {
		return president;
	}

	public void setPresident(Parliamentarian president) {
		this.president = president;
	}

	public Parliamentarian getFirst_vice_president() {
		return first_vice_president;
	}

	public void setFirst_vice_president(Parliamentarian first_vice_president) {
		this.first_vice_president = first_vice_president;
	}

	public Parliamentarian getSecond_vice_president() {
		return second_vice_president;
	}

	public void setSecond_vice_president(Parliamentarian second_vice_president) {
		this.second_vice_president = second_vice_president;
	}
}
