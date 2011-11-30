package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="campaign_details")
public class CampaignDetails {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="campaign_spending")
	private Integer campaignSpending;

	@Column(name="campaign_finance")
	private Integer campaignFinance;

	@Column(name="year")
	private Integer year;

	@Column(name="vote_number")
	private Integer voteNumber;

	@Column(name="vote_percentage")
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

}
