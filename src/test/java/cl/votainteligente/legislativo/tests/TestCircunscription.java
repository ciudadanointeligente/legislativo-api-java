package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Circunscription;

public class TestCircunscription extends TestCase {
	private EntityManager em;
	private Circunscription circunscription;
	private String name;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();
		circunscription = new Circunscription();
		name = "Maule Norte";
		circunscription.setName(name);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(circunscription);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(circunscription);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assertNotNull(em);
	}

	public void testGetId() {
		assertNotNull(circunscription.getId());
	}

	public void testGetName() {
		assertNotNull(circunscription.getName());
		assertEquals(circunscription.getName(), name);
	}
}