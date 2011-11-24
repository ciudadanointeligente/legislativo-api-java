package cl.votainteligente.legislativo.model;

public class Commune {

	private Long id;
	private String name;
	private District district;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}
	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}
}
