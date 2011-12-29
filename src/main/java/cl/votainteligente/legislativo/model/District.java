package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;

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

	/**
	 * @param circunscription
	 *            the circunscription to set
	 */
	public void setCircunscription(Circunscription circunscription) {
		this.circunscription = circunscription;
	}

	/**
	 * @return the circunscription
	 */
	public Circunscription getCircunscription() {
		return circunscription;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	@Transient
	public DistrictDO asDomainObject() {
		return new DistrictDO(this);
	}

	public void setCommunes(Set<Commune> communes) {
		this.communes = communes;
	}

	public Set<Commune> getCommunes() {
		return communes;
	}
}
