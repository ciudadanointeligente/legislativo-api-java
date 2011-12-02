package cl.votainteligente.legislativo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Stage {

	@Id
	@GeneratedValue
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date entryDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	// TODO: enumerate description
	private Long description;

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

	public void setDescription(Long description) {
		this.description = description;
	}

	public Long getDescription() {
		return description;
	}
}
