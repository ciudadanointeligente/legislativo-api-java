package cl.votainteligente.legislativo.model.DO;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Agrupation;

@XmlRootElement
public class AgrupationDO {
	private Long id;
	private String name;

	public AgrupationDO() {
	}

	public AgrupationDO(Agrupation agrupation) {
		this.id = agrupation.getId();
		this.name = agrupation.getName();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
