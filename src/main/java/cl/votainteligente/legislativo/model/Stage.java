package cl.votainteligente.legislativo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@ManyToOne
	@JoinColumn(name = "stage_description_id", nullable = false)
	private StageDescription stageDescription;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStageDescription(StageDescription stageDescription) {
		this.stageDescription = stageDescription;
	}

	public StageDescription getStageDescription() {
		return stageDescription;
	}
}
