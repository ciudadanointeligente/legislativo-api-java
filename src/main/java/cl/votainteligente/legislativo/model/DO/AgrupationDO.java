package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Agrupation;

import javax.xml.bind.annotation.XmlRootElement;

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
}
