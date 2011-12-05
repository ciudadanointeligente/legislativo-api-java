package cl.votainteligente.legislativo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cl.votainteligente.legislativo.model.domainobjects.CircunscriptionDO;

@Entity
@Table(name = "circunscription")
public class Circunscription {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	private String name;

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

	/**
	 * @param regions
	 *            the regions to set
	 */
	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

	/**
	 * @return the regions
	 */
	public Set<Region> getRegions() {
		return this.regions;
	}

	@Transient
	public CircunscriptionDO asDomainObject() {
		return new CircunscriptionDO(this);
	}
}
