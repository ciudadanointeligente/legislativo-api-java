package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Region;

public class TestDistrict extends TestCase {
	private EntityManager em;
	private District district;
	private Circunscription circunscription;
	private Region region;
	private String districtName;
	private String circunscriptionName;
	private String regionName;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		circunscription = new Circunscription();
		circunscriptionName = "Maule Norte";
		circunscription.setName(circunscriptionName);
		region = new Region();
		regionName = "Maule";
		region.setName(regionName);
		district = new District();
		districtName = "Maule";
		district.setName(districtName);
		district.setRegion(region);
		district.setCircunscription(circunscription);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(circunscription);
		em.persist(region);
		em.persist(district);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(district);
		em.remove(region);
		em.remove(circunscription);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assertNotNull(em);
	}

	public void testGetId() {
		assertNotNull(district.getId());
	}

	public void testGetName() {
		assertNotNull(district.getName());
		assertEquals(district.getName(), districtName);
	}

	public void testGetRegion() {
		assertNotNull(district.getRegion());
		assertEquals(district.getRegion(), region);
	}

	public void testGetCircunscription() {
		assertNotNull(district.getCircunscription());
		assertEquals(district.getCircunscription(), circunscription);
	}
}