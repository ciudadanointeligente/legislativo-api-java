package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.model.DO.PersonDetailedDO;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person extends Participant {

	@OneToMany(mappedBy = "person")
	private Set<SingleVote> personVotes;

	@OneToMany
	private List<Role> roles;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

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

	@Column(name = "university_education")
	private String universityEducation;

	@Column(name = "graduate_education")
	private String graduateEducation;

	@Column(name = "statement_of_interest")
	private String statementOfInterest;

	@Column(name = "statement_of_heritage")
	private String statementOfHeritage;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = { CascadeType.REMOVE })
	private Set<AgrupationAffiliation> agrupationAffiliations;

	public Set<SingleVote> getPersonVotes() {
		return personVotes;
	}

	public void setPersonVotes(Set<SingleVote> personVotes) {
		this.personVotes = personVotes;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Set<AgrupationAffiliation> getAgrupationAffiliations() {
		return agrupationAffiliations;
	}

	public void setAgrupationAffiliations(Set<AgrupationAffiliation> agrupationAffiliations) {
		this.agrupationAffiliations = agrupationAffiliations;
	}

	@Transient
	public PersonDO asDomainObject() {
		return new PersonDO(this);
	}

	@Transient
	public PersonDetailedDO asDetailedDomainObject() {
		return new PersonDetailedDO(this);
	}
}
