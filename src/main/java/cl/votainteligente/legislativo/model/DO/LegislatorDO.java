package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.Person;

import javax.xml.bind.annotation.XmlRootElement;

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
		if (legislator.getStartDate() != null) {
			this.startDate = DOUtil.getDateFormat().format(legislator.getStartDate());
		}
		if (legislator.getEndDate() != null) {
			this.endDate = DOUtil.getDateFormat().format(legislator.getEndDate());
		}
		this.allowance = legislator.getAllowance();
		Person person = legislator.getPerson();
		this.personId = person.getId();
		this.personFirstName = person.getFirstName();
		this.personLastName = person.getLastName();
		Chamber chamber = legislator.getChamber();
		this.chamberId = chamber.getId();
	}

	public Long getLegislatorId() {
		return legislatorId;
	}

	public void setLegislatorId(Long legislatorId) {
		this.legislatorId = legislatorId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonFirstName() {
		return personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	public Long getChamberId() {
		return chamberId;
	}

	public void setChamberId(Long chamberId) {
		this.chamberId = chamberId;
	}
}
