package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cl.votainteligente.legislativo.model.domainobjects.PersonDO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person extends Participant {

	@Column(name = "mail_address")
	private String mailAddress;

	@Column
	private String website;

	@Column(name = "twitter_account")
	private String twitterAccount;

	@Column(name = "facebook_account")
	private String facebookAccount;

	@Column
	private String gender;

	@Column
	private Date birthday;

	@Column
	private String profession;

	@Column
	private String party;

	@Column(name = "university_education")
	private String universityEducation;

	@Column(name = "graduate_education")
	private String graduateEducation;

	@Column(name = "statement_of_interest")
	private String statementOfInterest;

	@Column(name = "statement_of_heritage")
	private String statementOfHeritage;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Set<Role> roles = new HashSet<Role>();

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

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getUniversityEducation() {
		return universityEducation;
	}

	public void setUniversityEducation(String universityEducation) {
		this.universityEducation = universityEducation;
	}

	public String getGraduateEducation() {
		return graduateEducation;
	}

	public void setGraduateEducation(String graduateEducation) {
		this.graduateEducation = graduateEducation;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public PersonDO asDomainObject() {
		return new PersonDO(this);
	}
}