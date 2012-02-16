package cl.votainteligente.legislativo.model.DO;

import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.Region;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CircunscriptionDO {

	private Long id;
	private String name;
	private Set<Long> regions;

	public CircunscriptionDO() {
	}

	public CircunscriptionDO(Circunscription circunscription) {
		this.id = circunscription.getId();
		this.name = circunscription.getName();
		this.id = circunscription.getId();
		this.regions = new HashSet<Long>();

		Set<Region> regions = circunscription.getRegions();
		for (Region r : regions) {
			this.regions.add(r.getId());
		}
	}

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

	public Set<Long> getRegions() {
		return regions;
	}

	public void setRegions(Set<Long> regions) {
		this.regions = regions;
	}
}
