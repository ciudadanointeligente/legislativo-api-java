package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.Commune;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.Region;

public class TestCommune extends TestCase {

	private EntityManager em;
	private Commune commune;
	private String name;
	private District district;
	private Region region;
	private Circunscription circunscription;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		commune = new Commune();
		name = "Arica";
		district = new District();
		circunscription = new Circunscription();
		district.setCircunscription(circunscription);
		region = new Region();
		district.setRegion(region);
		commune.setName(name);
		commune.setDistrict(district);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(region);
		em.persist(circunscription);
		em.persist(district);
		em.persist(commune);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(commune);
		em.remove(district);
		em.remove(circunscription);
		em.remove(region);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testNewCommittee() {
		assertNotNull(commune.getId());
	}

	public void testGetName() {
		assertNotNull(commune.getName());
		assertEquals(commune.getName(), name);
	}

	public void testGetDistrict() {
		assertNotNull(commune.getDistrict());
		assertEquals(commune.getDistrict(), district);
	}
}
