package cl.votainteligente.legislativo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cl.votainteligente.legislativo.model.Activist;

import junit.framework.TestCase;

public class TestHibernate1 extends TestCase {
	// @PersistenceContext
	EntityManager em;

	@Override
	protected void setUp() throws Exception {
		// Everything you need to do before this class tests start
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();// */
	}

	@Override
	protected void tearDown() throws Exception {
		// Everything you need to do after this class tests end
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testActivistCreation() {
		Activist a = new Activist();
		a.setName("ActivistName");
		a.setPosition("ActivistPosition");
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		assert (a.getId() != null);
	}

}
