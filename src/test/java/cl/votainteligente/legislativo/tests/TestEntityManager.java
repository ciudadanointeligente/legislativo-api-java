package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import junit.framework.TestCase;

public class TestEntityManager extends TestCase {
	// @PersistenceContext
	EntityManager em;

	@Override
	protected void setUp() throws Exception {
		// Everything you need to do before this class tests start
		EntityManagerFactory emf = LocalEntityManager.factory();
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

}
