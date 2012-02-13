package cl.votainteligente.legislativo.model.DO;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.Stage;

@XmlRootElement
public class BillDetailedDO {

	private Long id;
	private String title;
	private String entryDate;
	private String updatedAt;
	private Set<Long> authorsId;
	private String bulletinNumber;
	private boolean published;
	private String publicationDate;
	private Long BCNLawId;
	private String BCNLawURL;
	private String initiative;
	private Long originChamberId;
	private String urgency;
	private Set<Long> stagesId;
	private Long matterId;
	private Long decree;
	private String summary;
	private Long SILProcessingsId;
	private Long SILOficiosId;
	private Long SILIndicationsId;
	private Long SILUrgenciesId;
	private Set<BillDO> mergedBills;

	public BillDetailedDO() {

	}

	public BillDetailedDO(Bill bill) {
		this.id = bill.getId();
		this.title = bill.getTitle();
		this.entryDate = DOUtil.getDateFormat().format(bill.getEntryDate());
		this.updatedAt = DOUtil.getDateFormat().format(bill.getUpdatedAt());
		this.bulletinNumber = bill.getBulletinNumber();
		if (this.isPublished())
			this.publicationDate = DOUtil.getDateFormat().format(
					bill.getPublicationDate());
		else
			this.publicationDate = "no-date";
		this.BCNLawId = bill.getBcnLawId();
		this.BCNLawURL = bill.getBcnLawURL();
		this.initiative = bill.getInitiative();
		this.originChamberId = bill.getOriginChamber().getId();
		this.urgency = bill.getUrgency();
		if (bill.getMatter() != null)
			this.matterId = bill.getMatter().getId();
		this.decree = bill.getDecree();
		this.summary = bill.getSummary();
		this.SILIndicationsId = bill.getSilIndicationsId();
		this.SILOficiosId = bill.getSilOficiosId();
		this.SILProcessingsId = bill.getSilProcessingsId();
		this.SILUrgenciesId = bill.getSilUrgenciesId();
		this.authorsId = new HashSet<Long>();
		Set<Person> billAuthors = bill.getAuthors();
		for (Person p : billAuthors)
			this.authorsId.add(p.getId());
		this.stagesId = new HashSet<Long>();
		Set<Stage> billStages = bill.getStages();
		for (Stage s : billStages)
			this.stagesId.add(s.getId());
		this.mergedBills = new HashSet<BillDO>();
		if(bill.getMergedBills() != null) {
			for (Bill mergedBill : bill.getMergedBills().getBills()) {
				if(!mergedBill.getBulletinNumber().equals(bill.getBulletinNumber()))
					mergedBills.add(mergedBill.asDomainObject());
			}
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @return the authorsId
	 */
	public Set<Long> getAuthorsId() {
		return authorsId;
	}

	/**
	 * @param authorsId
	 *            the authorsId to set
	 */
	public void setAuthorsId(Set<Long> authorsId) {
		this.authorsId = authorsId;
	}

	/**
	 * @return the bulletinNumber
	 */
	public String getBulletinNumber() {
		return bulletinNumber;
	}

	/**
	 * @param bulletinNumber
	 *            the bulletinNumber to set
	 */
	public void setBulletinNumber(String bulletinNumber) {
		this.bulletinNumber = bulletinNumber;
	}

	/**
	 * @return the published
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * @param published
	 *            the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
	}

	/**
	 * @return the publicationDate as String
	 */
	public String getPublicationDate() {
		return publicationDate;
	}

	/**
	 * @param publicationDate
	 *            the publicationDate to set as String
	 */
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * @return the bCNLawId
	 */
	public Long getBcnLawId() {
		return BCNLawId;
	}

	/**
	 * @param bCNLawId
	 *            the bCNLawId to set
	 */
	public void setBcnLawId(Long bCNLawId) {
		BCNLawId = bCNLawId;
	}

	/**
	 * @return the bCNLawURL
	 */
	public String getBcnLawURL() {
		return BCNLawURL;
	}

	/**
	 * @param bCNLawURL
	 *            the bCNLawURL to set
	 */
	public void setBcnLawURL(String bCNLawURL) {
		BCNLawURL = bCNLawURL;
	}

	/**
	 * @return the initiative
	 */
	public String getInitiative() {
		return initiative;
	}

	/**
	 * @param initiative
	 *            the initiative to set
	 */
	public void setInitiative(String initiative) {
		this.initiative = initiative;
	}

	/**
	 * @return the originChamber
	 */
	public Long getOriginChamberId() {
		return originChamberId;
	}

	/**
	 * @param originChamber
	 *            the originChamber to set
	 */
	public void setOriginChamber(Long originChamberId) {
		this.originChamberId = originChamberId;
	}

	/**
	 * @return the urgency
	 */
	public String getUrgency() {
		return urgency;
	}

	/**
	 * @param urgency
	 *            the urgency to set
	 */
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	/**
	 * @return the stages id
	 */
	public Set<Long> getStagesId() {
		return stagesId;
	}

	/**
	 * @param stages
	 *            the stages id to set
	 */
	public void setStages(Set<Long> stagesId) {
		this.stagesId = stagesId;
	}

	/**
	 * @return the matter id
	 */
	public Long getMatterId() {
		return matterId;
	}

	/**
	 * @param matter
	 *            the matter id to set
	 */
	public void setMatterId(Long matterId) {
		this.matterId = matterId;
	}

	/**
	 * @return the decree
	 */
	public Long getDecree() {
		return decree;
	}

	/**
	 * @param decree
	 *            the decree to set
	 */
	public void setDecree(Long decree) {
		this.decree = decree;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the sILProcessingsId
	 */
	public Long getSilProcessingsId() {
		return SILProcessingsId;
	}

	/**
	 * @param sILProcessingsId
	 *            the sILProcessingsId to set
	 */
	public void setSilProcessingsId(Long sILProcessingsId) {
		SILProcessingsId = sILProcessingsId;
	}

	/**
	 * @return the sILOficiosId
	 */
	public Long getSilOficiosId() {
		return SILOficiosId;
	}

	/**
	 * @param sILOficiosId
	 *            the sILOficiosId to set
	 */
	public void setSilOficiosId(Long sILOficiosId) {
		SILOficiosId = sILOficiosId;
	}

	/**
	 * @return the sILIndicationsId
	 */
	public Long getSilIndicationsId() {
		return SILIndicationsId;
	}

	/**
	 * @param sILIndicationsId
	 *            the sILIndicationsId to set
	 */
	public void setSilIndicationsId(Long sILIndicationsId) {
		SILIndicationsId = sILIndicationsId;
	}

	/**
	 * @return the sILUrgenciesId
	 */
	public Long getSilUrgenciesId() {
		return SILUrgenciesId;
	}

	/**
	 * @param sILUrgenciesId
	 *            the sILUrgenciesId to set
	 */
	public void setSilUrgenciesId(Long sILUrgenciesId) {
		SILUrgenciesId = sILUrgenciesId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the mergedBills
	 */
	public Set<BillDO> getMergedBills() {
		return mergedBills;
	}

	/**
	 * @param mergedBills
	 *            the mergedBills to set
	 */
	public void setMergedBills(Set<BillDO> mergedBills) {
		this.mergedBills = mergedBills;
	}
}