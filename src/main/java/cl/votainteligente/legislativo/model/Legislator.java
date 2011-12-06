package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "legislator")
public class Legislator extends Role {

	@ManyToOne
	@JoinColumn(name = "chamber_id", nullable = false)
	private Chamber chamber;

	@ManyToOne
	@JoinColumn(name = "circunscription_id")
	private Circunscription circunscription;

	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;

	// TODO: Asociar a objeto Comission
	@Column(name = "past_comissions")
	@Type(type = "text")
	private String pastComissions;

	@Column(name = "current_comissions")
	@Type(type = "text")
	private String currentComissions;

	@Column(name = "comite_envoy")
	@Type(type = "text")
	private String comiteEnvoy;

	// TODO: Ver si es necesario (o se hace query a tabla de gobernator)
	@Column(name = "government_charges")
	@Type(type = "text")
	private String governmentCharges;

	@Column(name = "election_charges")
	@Type(type = "text")
	private String electionCharges;

	// ID del sitio web de senado
	@Column(name = "parliament_site_id")
	private Integer parliamentSiteId;

	public Chamber getChamber() {
		return chamber;
	}

	public void setChamber(Chamber chamber) {
		this.chamber = chamber;
	}

	public Circunscription getCircunscription() {
		return circunscription;
	}

	public void setCircunscription(Circunscription circunscription) {
		this.circunscription = circunscription;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getPastComissions() {
		return pastComissions;
	}

	public void setPastComissions(String pastComissions) {
		this.pastComissions = pastComissions;
	}

	public String getCurrentComissions() {
		return currentComissions;
	}

	public void setCurrentComissions(String currentComissions) {
		this.currentComissions = currentComissions;
	}

	public String getComiteEnvoy() {
		return comiteEnvoy;
	}

	public void setComiteEnvoy(String comiteEnvoy) {
		this.comiteEnvoy = comiteEnvoy;
	}

	public String getGovernmentCharges() {
		return governmentCharges;
	}

	public void setGovernmentCharges(String governmentCharges) {
		this.governmentCharges = governmentCharges;
	}

	public String getElectionCharges() {
		return electionCharges;
	}

	public void setElectionCharges(String electionCharges) {
		this.electionCharges = electionCharges;
	}

	public Integer getParliamentSiteId() {
		return parliamentSiteId;
	}

	public void setParliamentSiteId(Integer parliamentSiteId) {
		this.parliamentSiteId = parliamentSiteId;
	}

}
