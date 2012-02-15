package cl.votainteligente.legislativo.model;

import cl.votainteligente.legislativo.model.DO.BillDO;
import cl.votainteligente.legislativo.model.DO.BillDetailedDO;

import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

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
	private Long bcnLawId;

	@Column(name = "bcn_law_url")
	private String bcnLawURL;
	private String initiative;

	@ManyToOne
	@JoinColumn(name = "origin_chamber")
	private Chamber originChamber;
	private String urgency;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Stage> stages;

	@ManyToOne
	private Matter matter;
	private Long decree;

	@Type(type = "text")
	private String summary;

	@Column(name = "sil_processings_id")
	private Long silProcessingsId;

	@Column(name = "sil_oficios_id")
	private Long silOficiosId;

	@Column(name = "sil_indications_id")
	private Long silIndicationsId;

	@Column(name = "sil_urgencies_id")
	private Long silUrgenciesId;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@Column
	private String type;

	@OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vote> votes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", cascade = { CascadeType.REMOVE })
	private Set<Debate> debates;

	@ManyToOne
	@JoinColumn(name = "merged_bills")
	private MergedBillContainer mergedBills;

	@Column(name = "decree_url")
	private String decreeUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Person> authors) {
		this.authors = authors;
	}

	public String getBulletinNumber() {
		return bulletinNumber;
	}

	public void setBulletinNumber(String bulletinNumber) {
		this.bulletinNumber = bulletinNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Long getBcnLawId() {
		return bcnLawId;
	}

	public void setBcnLawId(Long bcnLawId) {
		this.bcnLawId = bcnLawId;
	}

	public String getBcnLawURL() {
		return bcnLawURL;
	}

	public void setBcnLawURL(String bcnLawURL) {
		this.bcnLawURL = bcnLawURL;
	}

	public String getInitiative() {
		return initiative;
	}

	public void setInitiative(String initiative) {
		this.initiative = initiative;
	}

	public Chamber getOriginChamber() {
		return originChamber;
	}

	public void setOriginChamber(Chamber originChamber) {
		this.originChamber = originChamber;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public Set<Stage> getStages() {
		return stages;
	}

	public void setStages(Set<Stage> stages) {
		this.stages = stages;
	}

	public Matter getMatter() {
		return matter;
	}

	public void setMatter(Matter matter) {
		this.matter = matter;
	}

	public Long getDecree() {
		return decree;
	}

	public void setDecree(Long decree) {
		this.decree = decree;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getSilProcessingsId() {
		return silProcessingsId;
	}

	public void setSilProcessingsId(Long silProcessingsId) {
		this.silProcessingsId = silProcessingsId;
	}

	public Long getSilOficiosId() {
		return silOficiosId;
	}

	public void setSilOficiosId(Long silOficiosId) {
		this.silOficiosId = silOficiosId;
	}

	public Long getSilIndicationsId() {
		return silIndicationsId;
	}

	public void setSilIndicationsId(Long silIndicationsId) {
		this.silIndicationsId = silIndicationsId;
	}

	public Long getSilUrgenciesId() {
		return silUrgenciesId;
	}

	public void setSilUrgenciesId(Long silUrgenciesId) {
		this.silUrgenciesId = silUrgenciesId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public Set<Debate> getDebates() {
		return debates;
	}

	public void setDebates(Set<Debate> debates) {
		this.debates = debates;
	}

	public MergedBillContainer getMergedBills() {
		return mergedBills;
	}

	public void setMergedBills(MergedBillContainer mergedBills) {
		this.mergedBills = mergedBills;
	}

	public String getDecreeUrl() {
		return decreeUrl;
	}

	public void setDecreeUrl(String decreeUrl) {
		this.decreeUrl = decreeUrl;
	}

	@Transient
	public BillDO asDomainObject() {
		return new BillDO(this);
	}

	@Transient
	public BillDetailedDO asDetailedDomainObject() {
		return new BillDetailedDO(this);
	}
}
