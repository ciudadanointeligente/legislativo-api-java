package cl.votainteligente.legislativo.tests;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Chamber;

public class TestChamber extends TestCase {
	private EntityManager em;
	private Chamber chamber;
	private Timestamp createdAt, updatedAt;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = LocalEntityManager.factory();
		em = emf.createEntityManager();
		emf.close();

		createdAt = new Timestamp(123214);
		updatedAt = new Timestamp(123214);
		chamber = new Chamber();
		chamber.setName("Senado");
		chamber.setCreatedAt(createdAt);
		chamber.setUpdatedAt(updatedAt);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(chamber);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(chamber);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assertNotNull(em);
	}

	public void testGetId() {
		assertNotNull(chamber.getId());
	}

	public void testGetName() {
		assertNotNull(chamber.getName());
		assertEquals(chamber.getName(), "Senado");
	}

	public void testGetCreatedAt() {
		assertNotNull(chamber.getCreatedAt());
		assertEquals(chamber.getCreatedAt(), createdAt);
	}

	public void testGetUpdatedAt() {
		assertNotNull(chamber.getUpdatedAt());
		assertEquals(chamber.getUpdatedAt(), updatedAt);
	}
}