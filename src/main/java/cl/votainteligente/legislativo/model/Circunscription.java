package cl.votainteligente.legislativo.model;

import java.util.HashSet;
import java.util.Set;

public class Circunscription {

	private Long id;
	private String name;
	private Set<Region> regions = new HashSet<Region>();

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
	/**
	 * @param regions the regions to set
	 */
	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}
	/**
	 * @return the regions
	 */
	public Set<Region> getRegions() {
		return this.regions;
	}
}
