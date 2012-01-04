package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
}
