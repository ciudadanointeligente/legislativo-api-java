package cl.votainteligente.legislativo.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "substage")
public class Substage {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "entry_date")
	private Date entryDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Column
	private String description;

	@ManyToOne
	private Stage stage;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
