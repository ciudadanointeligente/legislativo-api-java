package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.AgrupationAffiliation;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.Role;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonDetailedDO {

	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthday;
	private List<Long> rolesId;
	private List<Long> groupAffiliatedIds;
	private String mailAddress;
	private String website;
	private String twitterAccount;
	private String facebookAccount;
	private String profession;
	private String universityEducation;
	private String graduateEducation;
	private String statementOfInterest;
	private String statementOfHeritage;

	public PersonDetailedDO() {
	}

	public PersonDetailedDO(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.gender = person.getGender();
		this.birthday = (person.getBirthday() != null) ? DOUtil.getDateFormat().format(person.getBirthday()) : null;
		this.mailAddress = person.getMailAddress();
		this.website = person.getWebsite();
		this.twitterAccount = person.getTwitterAccount();
		this.facebookAccount = person.getFacebookAccount();
		this.profession = person.getProfession();
		this.universityEducation = person.getUniversityEducation();
		this.graduateEducation = person.getGraduateEducation();
		this.statementOfHeritage = person.getStatementOfHeritage();
		this.statementOfInterest = person.getStatementOfInterest();
		this.rolesId = new ArrayList<Long>();
		List<Role> personRoles = person.getRoles();
		for (Role role : personRoles) {
			this.rolesId.add(role.getId());
		}
		this.groupAffiliatedIds = new ArrayList<Long>();
		for (AgrupationAffiliation agrupations : person.getAgrupationAffiliations()) {
			this.groupAffiliatedIds.add(agrupations.getAgrupation().getId());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<Long> getRolesId() {
		return rolesId;
	}

	public void setRolesId(List<Long> rolesId) {
		this.rolesId = rolesId;
	}

	public List<Long> getGroupAffiliatedIds() {
		return groupAffiliatedIds;
	}

	public void setGroupAffiliatedIds(List<Long> groupAffiliatedIds) {
		this.groupAffiliatedIds = groupAffiliatedIds;
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
}