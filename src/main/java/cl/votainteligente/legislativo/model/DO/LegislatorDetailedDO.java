package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.Person;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LegislatorDetailedDO {

	private Long legislatorId;
	private String startDate;
	private String endDate;
	private Integer allowance;
	private Long personId;
	private String personFirstName;
	private String personLastName;
	private Long chamberId;
	private String periodsDetails;
	private Long campaignSpending;
	private Long campaignFinance;
	private Long voteNumber;
	private Double votePercentage;
	private Long circunscriptionId;
	private Long districtId;
	private String pastComissions;
	private String currentComissions;
	private String comiteEnvoy;
	private String governmentCharges;
	private String electionCharges;
	private Integer parliamentSiteId;

	public LegislatorDetailedDO() {
	}

	public LegislatorDetailedDO(LegislatorRole legislator) {
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
		this.periodsDetails = legislator.getPeriodsDetails();
		this.campaignSpending = legislator.getCampaignSpending();
		this.campaignFinance = legislator.getCampaignFinance();
		this.voteNumber = legislator.getVoteNumber();
		this.votePercentage = legislator.getVotePercentage();
		if (legislator.getCircunscription() != null) {
			this.circunscriptionId = legislator.getCircunscription().getId();
		}
		if (legislator.getDistrict() != null) {
			this.districtId = legislator.getDistrict().getId();
		}
		this.pastComissions = legislator.getPastComissions();
		this.currentComissions = legislator.getCurrentComissions();
		this.comiteEnvoy = legislator.getComiteEnvoy();
		this.governmentCharges = legislator.getGovernmentCharges();
		this.electionCharges = legislator.getElectionCharges();
		this.parliamentSiteId = legislator.getParliamentSiteId();
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

	public String getPeriodsDetails() {
		return periodsDetails;
	}

	public void setPeriodsDetails(String periodsDetails) {
		this.periodsDetails = periodsDetails;
	}

	public Long getCampaignSpending() {
		return campaignSpending;
	}

	public void setCampaignSpending(Long campaignSpending) {
		this.campaignSpending = campaignSpending;
	}

	public Long getCampaignFinance() {
		return campaignFinance;
	}

	public void setCampaignFinance(Long campaignFinance) {
		this.campaignFinance = campaignFinance;
	}

	public Long getVoteNumber() {
		return voteNumber;
	}

	public void setVoteNumber(Long voteNumber) {
		this.voteNumber = voteNumber;
	}

	public Double getVotePercentage() {
		return votePercentage;
	}

	public void setVotePercentage(Double votePercentage) {
		this.votePercentage = votePercentage;
	}

	public Long getCircunscriptionId() {
		return circunscriptionId;
	}

	public void setCircunscriptionId(Long circunscriptionId) {
		this.circunscriptionId = circunscriptionId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getPastComissions() {
		return pastComissions;
	}

	public void setPastComissions(String pastComissions) {
		this.pastComissions = pastComissions;
	}

	public String getCurrentComissions() {
		return currentComissions;
	}

	public void setCurrentComissions(String currentComissions) {
		this.currentComissions = currentComissions;
	}

	public String getComiteEnvoy() {
		return comiteEnvoy;
	}

	public void setComiteEnvoy(String comiteEnvoy) {
		this.comiteEnvoy = comiteEnvoy;
	}

	public String getGovernmentCharges() {
		return governmentCharges;
	}

	public void setGovernmentCharges(String governmentCharges) {
		this.governmentCharges = governmentCharges;
	}

	public String getElectionCharges() {
		return electionCharges;
	}

	public void setElectionCharges(String electionCharges) {
		this.electionCharges = electionCharges;
	}

	public Integer getParliamentSiteId() {
		return parliamentSiteId;
	}

	public void setParliamentSiteId(Integer parliamentSiteId) {
		this.parliamentSiteId = parliamentSiteId;
	}
}
