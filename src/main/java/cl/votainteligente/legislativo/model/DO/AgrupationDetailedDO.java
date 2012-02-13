package cl.votainteligente.legislativo.model.DO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Agrupation;
import cl.votainteligente.legislativo.model.AgrupationAffiliation;

@XmlRootElement
public class AgrupationDetailedDO {
	private Long id;
	private String name;
	private String mailAddress;
	private String website;
	private String facebookAccount;
	private String twitterAccount;
	private String statementOfInterest;
	private String statementOfHeritage;
	private Date foundationDate;
	private List<Long> memberIds;

	public AgrupationDetailedDO() {
	};

	public AgrupationDetailedDO(Agrupation agrupation) {
		this.id = agrupation.getId();
		this.name = agrupation.getName();
		this.mailAddress = agrupation.getMailAddress();
		this.foundationDate = agrupation.getFoundationDate();
		this.website = agrupation.getWebsite();
		this.facebookAccount = agrupation.getFacebookAccount();
		this.twitterAccount = agrupation.getTwitterAccount();
		this.statementOfHeritage = agrupation.getStatementOfHeritage();
		this.statementOfInterest = agrupation.getStatementOfInterest();

		this.memberIds = new LinkedList<Long>();
		for (AgrupationAffiliation affiliations : agrupation
				.getAgrupationAffiliations())
			this.memberIds.add(affiliations.getPerson().getId());
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

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebookAccount() {
		return facebookAccount;
	}

	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	public String getTwitterAccount() {
		return twitterAccount;
	}

	public void setTwitterAccount(String twitterAccount) {
		this.twitterAccount = twitterAccount;
	}

	public String getStatementOfInterest() {
		return statementOfInterest;
	}

	public void setStatementOfInterest(String statementOfInterest) {
		this.statementOfInterest = statementOfInterest;
	}

	public String getStatementOfHeritage() {
		return statementOfHeritage;
	}

	public void setStatementOfHeritage(String statementOfHeritage) {
		this.statementOfHeritage = statementOfHeritage;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public List<Long> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<Long> memberIds) {
		this.memberIds = memberIds;
	}

}
