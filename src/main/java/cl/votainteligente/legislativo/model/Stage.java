package cl.votainteligente.legislativo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "stage")
public class Stage {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "entry_date")
	private Date entryDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Substage> subStages;

	@ManyToOne
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "stage_description_id", nullable = false)
	private StageDescription stageDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Substage> getSubStages() {
		return subStages;
	}

	public void setSubStages(Set<Substage> subStages) {
		this.subStages = subStages;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public StageDescription getStageDescription() {
		return stageDescription;
	}

	public void setStageDescription(StageDescription stageDescription) {
		this.stageDescription = stageDescription;
	}
}
