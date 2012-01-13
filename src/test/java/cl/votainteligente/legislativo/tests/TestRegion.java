package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Region;

public class TestRegion extends TestCase {
	private EntityManager em;
	private Region region;
	private String name;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = LocalEntityManager.factory();
		em = emf.createEntityManager();
		emf.close();
		region = new Region();
		name = "Atacama";
		region.setName(name);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(region);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(region);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assertNotNull(em);
	}

	public void testGetId() {
		assertNotNull(region.getId());
	}

	public void testGetName() {
		assertNotNull(region.getName());
		assertEquals(region.getName(), name);
	}
}
