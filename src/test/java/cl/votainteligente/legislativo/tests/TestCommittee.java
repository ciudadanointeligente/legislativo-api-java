package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Committee;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.Party;

public class TestCommittee extends TestCase {

	private EntityManager em;
	private Committee committee;
	private LegislatorRole president;
	private LegislatorRole presidentSubstitute;
	private Party party;
	private Chamber chamber;
	private String email;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		committee = new Committee();
		president = new LegislatorRole();
		presidentSubstitute = new LegislatorRole();
		chamber = new Chamber();
		party = new Party();
		email = "acommittee@senado.cl";

		president.setChamber(chamber);
		presidentSubstitute.setChamber(chamber);

		committee.setEmail(email);
		committee.setPresident(president);
		committee.setPresidentSubstitute(presidentSubstitute);
		committee.setParty(party);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(chamber);
		em.persist(party);
		em.persist(president);
		em.persist(presidentSubstitute);
		em.persist(committee);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(committee);
		em.remove(presidentSubstitute);
		em.remove(president);
		em.remove(party);
		em.remove(chamber);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testNewCommittee() {
		assertNotNull(committee.getId());
	}

	public void testGetEmail() {
		assertNotNull(committee.getEmail());
		assertEquals(committee.getEmail(), email);
	}

	public void testGetPresident() {
		assertNotNull(committee.getPresident());
		assertEquals(committee.getPresident(), president);
	}

	public void testGetPresidentSubstitute() {
		assertNotNull(committee.getPresidentSubstitute());
		assertEquals(committee.getPresidentSubstitute(), presidentSubstitute);
	}

	public void testGetParty() {
		assertNotNull(committee.getParty());
		assertEquals(committee.getParty(), party);
	}
}
