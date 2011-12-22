package cl.votainteligente.legislativo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "merged_bill")
public class MergedBill {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	@JoinColumn(name = "new_bill_id")
	private Bill newBill;

	@OneToMany
	private Set<Bill> mergedBills;

	@Temporal(TemporalType.DATE)
	@Column(name = "merged_date", nullable = false)
	private Date mergedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bill getNewBill() {
		return newBill;
	}

	public void setNewBill(Bill newBill) {
		this.newBill = newBill;
	}

	public Set<Bill> getMergedBills() {
		return mergedBills;
	}

	public void setMergedBills(Set<Bill> mergedBills) {
		this.mergedBills = mergedBills;
	}

	public Date getMergedDate() {
		return mergedDate;
	}

	public void setMergedDate(Date mergedDate) {
		this.mergedDate = mergedDate;
	}
}
