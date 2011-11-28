package cl.votainteligente.legislativo.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

@Entity
public class Chamber {

	/* FIXME: dummy class */
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(mappedBy="originChamber")
	private Set<Bill> bills = new HashSet<Bill>();
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the bills
	 */
	public Set<Bill> getBills() {
		return bills;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param bills the bills to set
	 */
	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

}
