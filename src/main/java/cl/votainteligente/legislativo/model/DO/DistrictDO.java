package cl.votainteligente.legislativo.model.DO;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.District;

@XmlRootElement
public class DistrictDO {
	private Long id;
	private String name;
	private Long circunscription;
	private Long region;

	public DistrictDO() {

	}

	public DistrictDO(District original) {
		this.id = original.getId();
		this.name = original.getName();
		this.circunscription = (original.getCircunscription() != null) ? original
				.getCircunscription().getId()
				: null;
		this.region = (original.getRegion() != null) ? original.getRegion()
				.getId() : null;
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
