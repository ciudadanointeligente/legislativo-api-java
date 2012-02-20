package cl.votainteligente.legislativo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "matter")
public class Matter {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Column(name = "super_matter")
	private String superMatter;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, mappedBy = "matter")
	private Set<Bill> bills;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperMatter() {
		return superMatter;
	}

	public void setSuperMatter(String superMatter) {
		this.superMatter = superMatter;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}
}
