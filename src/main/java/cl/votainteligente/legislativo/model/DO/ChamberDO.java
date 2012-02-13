package cl.votainteligente.legislativo.model.DO;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.LegislatorRole;

@XmlRootElement
public class ChamberDO {

	private Long id;
	private String name;
	private Long presidentId;
	private Long firstVicePresidentId;
	private Long secondVicePresidentId;

	public ChamberDO(Chamber chamber) {
		this.id = chamber.getId();
		this.name = chamber.getName();
		LegislatorRole president, firstVicePresident, secondVicePresident;
		president = chamber.getPresident();
		if (president != null)
			this.presidentId = president.getId();
		firstVicePresident = chamber.getFirstVicePresident();
		if (firstVicePresident != null)
			this.firstVicePresidentId = firstVicePresident.getId();
		secondVicePresident = chamber.getSecondVicePresident();
		if (secondVicePresident != null)
			this.secondVicePresidentId = secondVicePresident.getId();
	}

	public ChamberDO() {

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the presidentId
	 */
	public Long getPresidentId() {
		return presidentId;
	}

	/**
	 * @param presidentId
	 *            the presidentId to set
	 */
	public void setPresidentId(Long presidentId) {
		this.presidentId = presidentId;
	}

	/**
	 * @return the firstVicePresidentId
	 */
	public Long getFirstVicePresidentId() {
		return firstVicePresidentId;
	}

	/**
	 * @param firstVicePresidentId
	 *            the firstVicePresidentId to set
	 */
	public void setFirstVicePresidentId(Long firstVicePresidentId) {
		this.firstVicePresidentId = firstVicePresidentId;
	}

	/**
	 * @return the secondVicePresidentId
	 */
	public Long getSecondVicePresidentId() {
		return secondVicePresidentId;
	}

	/**
	 * @param secondVicePresidentId
	 *            the secondVicePresidentId to set
	 */
	public void setSecondVicePresidentId(Long secondVicePresidentId) {
		this.secondVicePresidentId = secondVicePresidentId;
	}

}
