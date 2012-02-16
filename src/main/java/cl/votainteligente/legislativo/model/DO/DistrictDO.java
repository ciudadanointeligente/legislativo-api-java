package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.District;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DistrictDO {

	private Long id;
	private String name;
	private Long circunscription;
	private Long region;

	public DistrictDO() {
	}

	public DistrictDO(District district) {
		this.id = district.getId();
		this.name = district.getName();
		this.circunscription = (district.getCircunscription() != null) ? district.getCircunscription().getId() : null;
		this.region = (district.getRegion() != null) ? district.getRegion().getId() : null;
	}

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

	public Long getCircunscription() {
		return circunscription;
	}

	public void setCircunscription(Long circunscription) {
		this.circunscription = circunscription;
	}

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}
}
