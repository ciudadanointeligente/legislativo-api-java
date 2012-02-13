package cl.votainteligente.legislativo.model.DO;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.Person;

@XmlRootElement
public class LegislatorDO {

	private Long legislatorId;
	private String startDate;
	private String endDate;
	private Integer allowance;
	private Long personId;
	private String personFirstName;
	private String personLastName;
	private Long chamberId;

	public LegislatorDO() {

	}

	public LegislatorDO(LegislatorRole legislator) {
		this.legislatorId = legislator.getId();
		if (legislator.getStartDate() != null)
			this.startDate = DOUtil.getDateFormat().format(
					legislator.getStartDate());
		if (legislator.getEndDate() != null)
			this.endDate = DOUtil.getDateFormat().format(
					legislator.getEndDate());
		this.allowance = legislator.getAllowance();
		Person p = legislator.getPerson();
		this.personId = p.getId();
		this.personFirstName = p.getFirstName();
		this.personLastName = p.getLastName();
		Chamber c = legislator.getChamber();
		this.chamberId = c.getId();
	}

	/**
	 * @return the legislatorId
	 */
	public Long getLegislatorId() {
		return legislatorId;
	}

	/**
	 * @param legislatorId
	 *            the legislatorId to set
	 */
	public void setLegislatorId(Long legislatorId) {
		this.legislatorId = legislatorId;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the allowance
	 */
	public Integer getAllowance() {
		return allowance;
	}

	/**
	 * @param allowance
	 *            the allowance to set
	 */
	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the personFirstName
	 */
	public String getPersonFirstName() {
		return personFirstName;
	}

	/**
	 * @param personFirstName
	 *            the personFirstName to set
	 */
	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	/**
	 * @return the personLastName
	 */
	public String getPersonLastName() {
		return personLastName;
	}

	/**
	 * @param personLastName
	 *            the personLastName to set
	 */
	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	/**
	 * @return the chamberId
	 */
	public Long getChamberId() {
		return chamberId;
	}

	/**
	 * @param chamberId
	 *            the chamberId to set
	 */
	public void setChamberId(Long chamberId) {
		this.chamberId = chamberId;
	}

}
