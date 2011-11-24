package cl.votainteligente.legislativo.model;

public class District {

	private Long id;
	private String name;
	private Circunscription circunscription;
	private Region region;

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
	 * @param circunscription the circunscription to set
	 */
	public void setCircunscription(Circunscription circunscription) {
		this.circunscription = circunscription;
	}
	/**
	 * @return the circunscription
	 */
	public Circunscription getCircunscription() {
		return circunscription;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}
}
