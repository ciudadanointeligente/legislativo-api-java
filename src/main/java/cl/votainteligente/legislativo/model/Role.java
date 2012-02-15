package cl.votainteligente.legislativo.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "period_details")
	private String periodsDetails;

	@Column
	private Integer allowance;

	@Column(name = "campaign_spending")
	private Long campaignSpending;

	@Column(name = "campaign_finance")
	private Long campaignFinance;

	@Column(name = "vote_number")
	private Long voteNumber;

	@Column(name = "vote_percentage")
	private Double votePercentage;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPeriodsDetails() {
		return periodsDetails;
	}

	public void setPeriodsDetails(String periodsDetails) {
		this.periodsDetails = periodsDetails;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
