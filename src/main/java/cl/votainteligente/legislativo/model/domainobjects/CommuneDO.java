package cl.votainteligente.legislativo.model.domainobjects;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Commune;

@XmlRootElement
public class CommuneDO {
	private Long id;
	private String name;
	private Long district;

	public CommuneDO() {

	}

	public CommuneDO(Commune c) {
		this.id = c.getId();
		this.name = c.getName();
		this.district = (c.getDistrict() != null) ? c.getDistrict().getId()
				: null;
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
