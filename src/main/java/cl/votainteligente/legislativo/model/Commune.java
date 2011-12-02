package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cl.votainteligente.legislativo.model.domainobjects.CommuneDO;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	@Transient
	public CommuneDO asDomainObject() {
		return new CommuneDO(this);
	}
}
