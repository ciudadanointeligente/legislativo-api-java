package cl.votainteligente.legislativo.model.DO;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.AgrupationAffiliation;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.Role;

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

	public String getBirthday() {
		return birthday;
	}

	public PersonDetailedDO() {

	}

	public PersonDetailedDO(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.gender = person.getGender();
		this.birthday = (person.getBirthday() != null) ? DOUtil.getDateFormat()
				.format(person.getBirthday()) : null;
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
		for (Role role : personRoles)
			this.rolesId.add(role.getId());
		this.groupAffiliatedIds = new ArrayList<Long>();
		for (AgrupationAffiliation agrupations : person
				.getAgrupationAffiliations())
			this.groupAffiliatedIds.add(agrupations.getAgrupation().getId());
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the createdAt
	 */

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	/**
	 * @return the rolesId
	 */
	public List<Long> getRolesId() {
		return rolesId;
	}

	/**
	 * @param rolesId
	 *            the rolesId to set
	 */
	public void setRolesId(List<Long> rolesId) {
		this.rolesId = rolesId;
	}

	public List<Long> getGroupAffiliatedIds() {
		return groupAffiliatedIds;
	}

	public void setGroupAffiliatedIds(List<Long> groupAffiliatedIds) {
		this.groupAffiliatedIds = groupAffiliatedIds;
	}

	/**
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress
	 *            the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the twitterAccount
	 */
	public String getTwitterAccount() {
		return twitterAccount;
	}

	/**
	 * @param twitterAccount
	 *            the twitterAccount to set
	 */
	public void setTwitterAccount(String twitterAccount) {
		this.twitterAccount = twitterAccount;
	}

	/**
	 * @return the facebookAccount
	 */
	public String getFacebookAccount() {
		return facebookAccount;
	}

	/**
	 * @param facebookAccount
	 *            the facebookAccount to set
	 */
	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession
	 *            the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the universityEducation
	 */
	public String getUniversityEducation() {
		return universityEducation;
	}

	/**
	 * @param universityEducation
	 *            the universityEducation to set
	 */
	public void setUniversityEducation(String universityEducation) {
		this.universityEducation = universityEducation;
	}

	/**
	 * @return the graduateEducation
	 */
	public String getGraduateEducation() {
		return graduateEducation;
	}

	/**
	 * @param graduateEducation
	 *            the graduateEducation to set
	 */
	public void setGraduateEducation(String graduateEducation) {
		this.graduateEducation = graduateEducation;
	}

	/**
	 * @return the statementOfInterest
	 */
	public String getStatementOfInterest() {
		return statementOfInterest;
	}

	/**
	 * @param statementOfInterest
	 *            the statementOfInterest to set
	 */
	public void setStatementOfInterest(String statementOfInterest) {
		this.statementOfInterest = statementOfInterest;
	}

	/**
	 * @return the statementOfHeritage
	 */
	public String getStatementOfHeritage() {
		return statementOfHeritage;
	}

	/**
	 * @param statementOfHeritage
	 *            the statementOfHeritage to set
	 */
	public void setStatementOfHeritage(String statementOfHeritage) {
		this.statementOfHeritage = statementOfHeritage;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}