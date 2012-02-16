package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.Stage;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

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
	private Long SILOfficialDocumentId;
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
		if (this.isPublished()) {
			this.publicationDate = DOUtil.getDateFormat().format(bill.getPublicationDate());
		} else {
			this.publicationDate = "no-date";
		}
		this.BCNLawId = bill.getBcnLawId();
		this.BCNLawURL = bill.getBcnLawURL();
		this.initiative = bill.getInitiative();
		this.originChamberId = bill.getOriginChamber().getId();
		this.urgency = bill.getUrgency();
		if (bill.getMatter() != null) {
			this.matterId = bill.getMatter().getId();
		}
		this.decree = bill.getDecree();
		this.summary = bill.getSummary();
		this.SILIndicationsId = bill.getSilIndicationsId();
		this.SILOfficialDocumentId = bill.getSilOficiosId();
		this.SILProcessingsId = bill.getSilProcessingsId();
		this.SILUrgenciesId = bill.getSilUrgenciesId();
		this.authorsId = new HashSet<Long>();
		Set<Person> billAuthors = bill.getAuthors();
		for (Person p : billAuthors) {
			this.authorsId.add(p.getId());
		}
		this.stagesId = new HashSet<Long>();
		Set<Stage> billStages = bill.getStages();
		for (Stage s : billStages) {
			this.stagesId.add(s.getId());
		}
		this.mergedBills = new HashSet<BillDO>();
		if (bill.getMergedBills() != null) {
			for (Bill mergedBill : bill.getMergedBills().getBills()) {
				if (!mergedBill.getBulletinNumber().equals(bill.getBulletinNumber())) {
					mergedBills.add(mergedBill.asDomainObject());
				}
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Long> getAuthorsId() {
		return authorsId;
	}

	public void setAuthorsId(Set<Long> authorsId) {
		this.authorsId = authorsId;
	}

	public String getBulletinNumber() {
		return bulletinNumber;
	}

	public void setBulletinNumber(String bulletinNumber) {
		this.bulletinNumber = bulletinNumber;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Long getBCNLawId() {
		return BCNLawId;
	}

	public void setBCNLawId(Long BCNLawId) {
		this.BCNLawId = BCNLawId;
	}

	public String getBCNLawURL() {
		return BCNLawURL;
	}

	public void setBCNLawURL(String BCNLawURL) {
		this.BCNLawURL = BCNLawURL;
	}

	public String getInitiative() {
		return initiative;
	}

	public void setInitiative(String initiative) {
		this.initiative = initiative;
	}

	public Long getOriginChamberId() {
		return originChamberId;
	}

	public void setOriginChamberId(Long originChamberId) {
		this.originChamberId = originChamberId;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public Set<Long> getStagesId() {
		return stagesId;
	}

	public void setStagesId(Set<Long> stagesId) {
		this.stagesId = stagesId;
	}

	public Long getMatterId() {
		return matterId;
	}

	public void setMatterId(Long matterId) {
		this.matterId = matterId;
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

	public Long getSILProcessingsId() {
		return SILProcessingsId;
	}

	public void setSILProcessingsId(Long SILProcessingsId) {
		this.SILProcessingsId = SILProcessingsId;
	}

	public Long getSILOfficialDocumentId() {
		return SILOfficialDocumentId;
	}

	public void setSILOfficialDocumentId(Long SILOfficialDocumentId) {
		this.SILOfficialDocumentId = SILOfficialDocumentId;
	}

	public Long getSILIndicationsId() {
		return SILIndicationsId;
	}

	public void setSILIndicationsId(Long SILIndicationsId) {
		this.SILIndicationsId = SILIndicationsId;
	}

	public Long getSILUrgenciesId() {
		return SILUrgenciesId;
	}

	public void setSILUrgenciesId(Long SILUrgenciesId) {
		this.SILUrgenciesId = SILUrgenciesId;
	}

	public Set<BillDO> getMergedBills() {
		return mergedBills;
	}

	public void setMergedBills(Set<BillDO> mergedBills) {
		this.mergedBills = mergedBills;
	}
}
