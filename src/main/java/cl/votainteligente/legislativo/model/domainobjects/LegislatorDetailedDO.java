package cl.votainteligente.legislativo.model.domainobjects;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Legislator;
import cl.votainteligente.legislativo.model.Person;

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
	private Integer campaignSpending;
	private Integer campaignFinance;
	private Integer voteNumber;
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

	public LegislatorDetailedDO(Legislator legislator) {
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
		this.periodsDetails = legislator.getPeriodsDetails();
		this.campaignSpending = legislator.getCampaignSpending();
		this.campaignFinance = legislator.getCampaignFinance();
		this.voteNumber = legislator.getVoteNumber();
		this.votePercentage = legislator.getVotePercentage();
		if (legislator.getCircunscription() != null)
			this.circunscriptionId = legislator.getCircunscription().getId();
		if (legislator.getDistrict() != null)
			this.districtId = legislator.getDistrict().getId();
		this.pastComissions = legislator.getPastComissions();
		this.currentComissions = legislator.getCurrentComissions();
		this.comiteEnvoy = legislator.getComiteEnvoy();
		this.governmentCharges = legislator.getGovernmentCharges();
		this.electionCharges = legislator.getElectionCharges();
		this.parliamentSiteId = legislator.getParliamentSiteId();
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

	/**
	 * @return the periodsDetails
	 */
	public String getPeriodsDetails() {
		return periodsDetails;
	}

	/**
	 * @param periodsDetails
	 *            the periodsDetails to set
	 */
	public void setPeriodsDetails(String periodsDetails) {
		this.periodsDetails = periodsDetails;
	}

	/**
	 * @return the campaignSpending
	 */
	public Integer getCampaignSpending() {
		return campaignSpending;
	}

	/**
	 * @param campaignSpending
	 *            the campaignSpending to set
	 */
	public void setCampaignSpending(Integer campaignSpending) {
		this.campaignSpending = campaignSpending;
	}

	/**
	 * @return the campaignFinance
	 */
	public Integer getCampaignFinance() {
		return campaignFinance;
	}

	/**
	 * @param campaignFinance
	 *            the campaignFinance to set
	 */
	public void setCampaignFinance(Integer campaignFinance) {
		this.campaignFinance = campaignFinance;
	}

	/**
	 * @return the voteNumber
	 */
	public Integer getVoteNumber() {
		return voteNumber;
	}

	/**
	 * @param voteNumber
	 *            the voteNumber to set
	 */
	public void setVoteNumber(Integer voteNumber) {
		this.voteNumber = voteNumber;
	}

	/**
	 * @return the votePercentage
	 */
	public Double getVotePercentage() {
		return votePercentage;
	}

	/**
	 * @param votePercentage
	 *            the votePercentage to set
	 */
	public void setVotePercentage(Double votePercentage) {
		this.votePercentage = votePercentage;
	}

	/**
	 * @return the circunscriptionId
	 */
	public Long getCircunscriptionId() {
		return circunscriptionId;
	}

	/**
	 * @param circunscriptionId
	 *            the circunscriptionId to set
	 */
	public void setCircunscriptionId(Long circunscriptionId) {
		this.circunscriptionId = circunscriptionId;
	}

	/**
	 * @return the districtId
	 */
	public Long getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId
	 *            the districtId to set
	 */
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the pastComissions
	 */
	public String getPastComissions() {
		return pastComissions;
	}

	/**
	 * @param pastComissions
	 *            the pastComissions to set
	 */
	public void setPastComissions(String pastComissions) {
		this.pastComissions = pastComissions;
	}

	/**
	 * @return the currentComissions
	 */
	public String getCurrentComissions() {
		return currentComissions;
	}

	/**
	 * @param currentComissions
	 *            the currentComissions to set
	 */
	public void setCurrentComissions(String currentComissions) {
		this.currentComissions = currentComissions;
	}

	/**
	 * @return the comiteEnvoy
	 */
	public String getComiteEnvoy() {
		return comiteEnvoy;
	}

	/**
	 * @param comiteEnvoy
	 *            the comiteEnvoy to set
	 */
	public void setComiteEnvoy(String comiteEnvoy) {
		this.comiteEnvoy = comiteEnvoy;
	}

	/**
	 * @return the governmentCharges
	 */
	public String getGovernmentCharges() {
		return governmentCharges;
	}

	/**
	 * @param governmentCharges
	 *            the governmentCharges to set
	 */
	public void setGovernmentCharges(String governmentCharges) {
		this.governmentCharges = governmentCharges;
	}

	/**
	 * @return the electionCharges
	 */
	public String getElectionCharges() {
		return electionCharges;
	}

	/**
	 * @param electionCharges
	 *            the electionCharges to set
	 */
	public void setElectionCharges(String electionCharges) {
		this.electionCharges = electionCharges;
	}

	/**
	 * @return the parliamentSiteId
	 */
	public Integer getParliamentSiteId() {
		return parliamentSiteId;
	}

	/**
	 * @param parliamentSiteId
	 *            the parliamentSiteId to set
	 */
	public void setParliamentSiteId(Integer parliamentSiteId) {
		this.parliamentSiteId = parliamentSiteId;
	}
}
