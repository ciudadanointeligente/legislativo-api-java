package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Commune;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommuneDO {

	private Long id;
	private String name;
	private Long district;

	public CommuneDO() {
	}

	public CommuneDO(Commune commune) {
		this.id = commune.getId();
		this.name = commune.getName();
		this.district = (commune.getDistrict() != null) ? commune.getDistrict().getId() : null;
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

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}
}
