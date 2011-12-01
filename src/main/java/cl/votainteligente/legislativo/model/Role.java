package cl.votainteligente.legislativo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Long id;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "period_details")
	private String periodsDetails;

	@Column
	private Integer allowance;

	@Column(name = "campaign_spending")
	private Integer campaignSpending;

	@Column(name = "campaign_finance")
	private Integer campaignFinance;

	@Column(name = "vote_number")
	private Integer voteNumber;

	@Column(name = "vote_percentage")
	private Double votePercentage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCampaignSpending() {
		return campaignSpending;
	}

	public void setCampaignSpending(Integer campaignSpending) {
		this.campaignSpending = campaignSpending;
	}

	public Integer getCampaignFinance() {
		return campaignFinance;
	}

	public void setCampaignFinance(Integer campaignFinance) {
		this.campaignFinance = campaignFinance;
	}

	public Integer getVoteNumber() {
		return voteNumber;
	}

	public void setVoteNumber(Integer voteNumber) {
		this.voteNumber = voteNumber;
	}

	public Double getVotePercentage() {
		return votePercentage;
	}

	public void setVotePercentage(Double votePercentage) {
		this.votePercentage = votePercentage;
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

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	public String getPeriodsDetails() {
		return periodsDetails;
	}

	public void setPeriodsDetails(String periodsDetails) {
		this.periodsDetails = periodsDetails;
	}
}