package cl.votainteligente.legislativo.model;

import java.net.URL;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coalition")
public class Coalition {

	@Id
	@GeneratedValue
	private Long id;

	private String address;
	private String name;
	private URL web;

	private Date foundationDate;
	private Date dissolutionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getWeb() {
		return web;
	}

	public void setWeb(URL web) {
		this.web = web;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public Date getDissolutionDate() {
		return dissolutionDate;
	}

	public void setDissolutionDate(Date dissolutionDate) {
		this.dissolutionDate = dissolutionDate;
	}
}
