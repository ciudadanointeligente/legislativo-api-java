package cl.votainteligente.legislativo.model;

import java.util.Date;
import javax.persistence.*;

import cl.votainteligente.legislativo.model.domainobjects.BillDO;

@Entity
public class Bill {

	@Id
	@GeneratedValue
	private Long id;
	private String bulletinNumber;
	private String title;
	private Date entryDate;
	private boolean published;
	private Date publicationDate;
	private Long BCNLawId;
	private String BCNLawURL;
	private String initiative;
	@ManyToOne
	private Chamber originChamber;
	private String urgency;
	private String stage;
	private String subStage;
	@ManyToOne
	private Matter matter;
	private Long decree;
	private String summary;
	private Long SILProcessingsId;
	private Long SILOficiosId;
	private Long SILIndicationsId;
	private Long SILUrgenciesId;
	private Date createdAt;
	private Date updatedAt;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the bulletinNumber
	 */
	public String getBulletinNumber() {
		return bulletinNumber;
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
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @return the published
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * @return the publicationDate
	 */
	public Date getPublicationDate() {
		return publicationDate;
	}

	/**
	 * @return the bCNLawId
	 */
	public Long getBCNLawId() {
		return BCNLawId;
	}

	/**
	 * @return the bCNLawURL
	 */
	public String getBCNLawURL() {
		return BCNLawURL;
	}

	/**
	 * @return the initiative
	 */
	public String getInitiative() {
		return initiative;
	}

	/**
	 * @return the originChamber
	 */
	public Chamber getOriginChamber() {
		return originChamber;
	}

	/**
	 * @return the urgency
	 */
	public String getUrgency() {
		return urgency;
	}

	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}

	/**
	 * @return the subStage
	 */
	public String getSubStage() {
		return subStage;
	}

	/**
	 * @return the matter
	 */
	public Matter getMatter() {
		return matter;
	}

	/**
	 * @return the decree
	 */
	public Long getDecree() {
		return decree;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @return the sILProcessingsId
	 */
	public Long getSILProcessingsId() {
		return SILProcessingsId;
	}

	/**
	 * @return the sILOficiosId
	 */
	public Long getSILOficiosId() {
		return SILOficiosId;
	}

	/**
	 * @return the sILIndicationsId
	 */
	public Long getSILIndicationsId() {
		return SILIndicationsId;
	}

	/**
	 * @return the sILUrgenciesId
	 */
	public Long getSILUrgenciesId() {
		return SILUrgenciesId;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param bulletinNumber
	 *            the bulletinNumber to set
	 */
	public void setBulletinNumber(String bulletinNumber) {
		this.bulletinNumber = bulletinNumber;
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
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @param published
	 *            the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
	}

	/**
	 * @param publicationDate
	 *            the publicationDate to set
	 */
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * @param bCNLawId
	 *            the bCNLawId to set
	 */
	public void setBCNLawId(Long bCNLawId) {
		BCNLawId = bCNLawId;
	}

	/**
	 * @param bCNLawURL
	 *            the bCNLawURL to set
	 */
	public void setBCNLawURL(String bCNLawURL) {
		BCNLawURL = bCNLawURL;
	}

	/**
	 * @param initiative
	 *            the initiative to set
	 */
	public void setInitiative(String initiative) {
		this.initiative = initiative;
	}

	/**
	 * @param originChamber
	 *            the originChamber to set
	 */
	public void setOriginChamber(Chamber originChamber) {
		this.originChamber = originChamber;
	}

	/**
	 * @param urgency
	 *            the urgency to set
	 */
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}

	/**
	 * @param subStage
	 *            the subStage to set
	 */
	public void setSubStage(String subStage) {
		this.subStage = subStage;
	}

	/**
	 * @param matter
	 *            the matter to set
	 */
	public void setMatter(Matter matter) {
		this.matter = matter;
	}

	/**
	 * @param decree
	 *            the decree to set
	 */
	public void setDecree(Long decree) {
		this.decree = decree;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @param sILProcessingsId
	 *            the sILProcessingsId to set
	 */
	public void setSILProcessingsId(Long sILProcessingsId) {
		SILProcessingsId = sILProcessingsId;
	}

	/**
	 * @param sILOficiosId
	 *            the sILOficiosId to set
	 */
	public void setSILOficiosId(Long sILOficiosId) {
		SILOficiosId = sILOficiosId;
	}

	/**
	 * @param sILIndicationsId
	 *            the sILIndicationsId to set
	 */
	public void setSILIndicationsId(Long sILIndicationsId) {
		SILIndicationsId = sILIndicationsId;
	}

	/**
	 * @param sILUrgenciesId
	 *            the sILUrgenciesId to set
	 */
	public void setSILUrgenciesId(Long sILUrgenciesId) {
		SILUrgenciesId = sILUrgenciesId;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Transient
	public BillDO asDomainObject() {
		return new BillDO(this);
	}
}
