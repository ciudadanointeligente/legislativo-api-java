package cl.votainteligente.legislativo.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="role")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="id")
	private Long id;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;

	@Column(name="period_details")
	private String periodsDetails;

	@Column
	private Integer allowance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	public String getPeriodsDetails() {
		return periodsDetails;
	}

	public void setPeriodsDetails(String periodsDetails) {
		this.periodsDetails = periodsDetails;
	}
}