package cl.votainteligente.legislativo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.BillDetailedDO;

@Entity
@Table(name = "bill")
public class Bill {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToMany
	private Set<Person> authors;
	@Column(name = "bulletin_number")
	private String bulletinNumber;
	@Type(type = "text")
	private String title;
	@Temporal(TemporalType.DATE)
	@Column(name = "entry_date", nullable = false)
	private Date entryDate;
	private boolean published;
	@Column(name = "publication_date")
	@Temporal(TemporalType.DATE)
	private Date publicationDate;
	@Column(name = "bcn_law_id")
	private Long BCNLawId;
	@Column(name = "bcn_law_url")
	private String BCNLawURL;
	private String initiative;
	@ManyToOne
	@JoinColumn(name = "origin_chamber")
	private Chamber originChamber;
	private String urgency;
	@OneToMany
	private Set<Stage> stages;
	@ManyToOne
	private Matter matter;
	private Long decree;
	@Type(type = "text")
	private String summary;
	@Column(name = "sil_processings_id")
	private Long SILProcessingsId;
	@Column(name = "sil_oficios_id")
	private Long SILOficiosId;
	@Column(name = "sil_indications_id")
	private Long SILIndicationsId;
	@Column(name = "sil_urgencies_id")
	private Long SILUrgenciesId;
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	private Date createdAt;
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;
	@Column
	private String type;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public Set<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Person> authors) {
		this.authors = authors;
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

	@Transient
	public BillDetailedDO asDetailedDomainObject() {
		return new BillDetailedDO(this);
	}

	public void setStages(Set<Stage> stages) {
		this.stages = stages;
	}

	public Set<Stage> getStages() {
		return stages;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}