package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.DistrictDO;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "district")
public class District {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "circunscription_id", nullable = false)
	private Circunscription circunscription;

	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;

	@OneToMany(mappedBy = "district", cascade = { CascadeType.REMOVE })
	private Set<Commune> communes;

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

	public Circunscription getCircunscription() {
		return circunscription;
	}

	public void setCircunscription(Circunscription circunscription) {
		this.circunscription = circunscription;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Set<Commune> getCommunes() {
		return communes;
	}

	public void setCommunes(Set<Commune> communes) {
		this.communes = communes;
	}

	@Transient
	public DistrictDO asDomainObject() {
		return new DistrictDO(this);
	}
}
