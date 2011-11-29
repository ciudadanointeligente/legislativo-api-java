package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "activist")
public class Activist {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String position;

	@OneToOne
	@JoinColumn(name = "parliamentarian_id", nullable = true)
	private Parliamentarian parliamentarian;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Parliamentarian getParliamentarian() {
		return parliamentarian;
	}

	public void setParliamentarian(Parliamentarian parliamentarian) {
		this.parliamentarian = parliamentarian;
	}

}
