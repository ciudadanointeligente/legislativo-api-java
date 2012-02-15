package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.AgrupationDO;
import cl.votainteligente.legislativo.model.DO.AgrupationDetailedDO;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "agrupation")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Agrupation extends Participant {

	@Column(name = "mail_address")
	private String mailAddress;

	@Column
	private String website;

	@Column(name = "twitter_account")
	private String twitterAccount;

	@Column(name = "facebook_account")
	private String facebookAccount;

	@Column(name = "foundation_date")
	private Date foundationDate;

	@Column
	private String name;

	@Column(name = "statement_of_interest")
	private String statementOfInterest;

	@Column(name = "statement_of_heritage")
	private String statementOfHeritage;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agrupation", cascade = { CascadeType.REMOVE })
	private Set<AgrupationAffiliation> agrupationAffiliations;

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

	public String getTwitterAccount() {
		return twitterAccount;
	}

	public void setTwitterAccount(String twitterAccount) {
		this.twitterAccount = twitterAccount;
	}

	public String getFacebookAccount() {
		return facebookAccount;
	}

	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<AgrupationAffiliation> getAgrupationAffiliations() {
		return agrupationAffiliations;
	}

	public void setAgrupationAffiliations(Set<AgrupationAffiliation> agrupationAffiliations) {
		this.agrupationAffiliations = agrupationAffiliations;
	}

	@Transient
	public AgrupationDO asDomainObject() {
		return new AgrupationDO(this);
	}

	@Transient
	public AgrupationDetailedDO asDetailedDomainObject() {
		return new AgrupationDetailedDO(this);
	}
}
