package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.CommuneDO;

import javax.persistence.*;

@Entity
@Table(name = "commune")
public class Commune {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "district_id", nullable = false)
	private District district;

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Transient
	public CommuneDO asDomainObject() {
		return new CommuneDO(this);
	}
}
