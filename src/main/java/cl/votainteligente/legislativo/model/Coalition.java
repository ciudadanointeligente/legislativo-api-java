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

	private Date foundation_date;
	private Date dissolution_date;

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

	public Date getFoundation_date() {
		return foundation_date;
	}

	public void setFoundation_date(Date foundation_date) {
		this.foundation_date = foundation_date;
	}

	public Date getDissolution_date() {
		return dissolution_date;
	}

	public void setDissolution_date(Date dissolution_date) {
		this.dissolution_date = dissolution_date;
	}
}
