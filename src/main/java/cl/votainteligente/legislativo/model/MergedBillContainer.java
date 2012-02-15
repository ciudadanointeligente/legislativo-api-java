package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "merged_bill")
public class MergedBillContainer {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "mergedBills")
	private Set<Bill> bills;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}
}
