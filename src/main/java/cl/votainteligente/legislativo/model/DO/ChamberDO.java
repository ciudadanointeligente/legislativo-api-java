package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.LegislatorRole;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChamberDO {

	private Long id;
	private String name;
	private Long presidentId;
	private Long firstVicePresidentId;
	private Long secondVicePresidentId;

	public ChamberDO() {
	}

	public ChamberDO(Chamber chamber) {
		this.id = chamber.getId();
		this.name = chamber.getName();
		LegislatorRole president, firstVicePresident, secondVicePresident;
		president = chamber.getPresident();
		if (president != null) {
			this.presidentId = president.getId();
		}
		firstVicePresident = chamber.getFirstVicePresident();
		if (firstVicePresident != null) {
			this.firstVicePresidentId = firstVicePresident.getId();
		}
		secondVicePresident = chamber.getSecondVicePresident();
		if (secondVicePresident != null) {
			this.secondVicePresidentId = secondVicePresident.getId();
		}
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

	public Long getPresidentId() {
		return presidentId;
	}

	public void setPresidentId(Long presidentId) {
		this.presidentId = presidentId;
	}

	public Long getFirstVicePresidentId() {
		return firstVicePresidentId;
	}

	public void setFirstVicePresidentId(Long firstVicePresidentId) {
		this.firstVicePresidentId = firstVicePresidentId;
	}

	public Long getSecondVicePresidentId() {
		return secondVicePresidentId;
	}

	public void setSecondVicePresidentId(Long secondVicePresidentId) {
		this.secondVicePresidentId = secondVicePresidentId;
	}
}
