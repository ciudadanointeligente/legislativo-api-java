package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.CircunscriptionDO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "circunscription")
public class Circunscription {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	private String name;

	@OneToMany(mappedBy = "circunscription", cascade = { CascadeType.REMOVE })
	private Set<District> districts;

	@ManyToMany
	@JoinColumn(name = "region_id", nullable = false)
	private Set<Region> regions = new HashSet<Region>();

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

	public Set<District> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}

	public Set<Region> getRegions() {
		return regions;
	}

	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

	@Transient
	public CircunscriptionDO asDomainObject() {
		return new CircunscriptionDO(this);
	}
}
