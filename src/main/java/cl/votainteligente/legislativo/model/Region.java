package cl.votainteligente.legislativo.model;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	private String name;
	private String initials;

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
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
}
