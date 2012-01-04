package cl.votainteligente.legislativo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.LegislatorRole;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;

public class TestSingleVote extends TestCase {

	private EntityManager em;
	private SingleVote singleVote;
	private LegislatorRole legislatorRole;
	private Chamber chamber;
	private Vote vote;
	private String voteDetail;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();
		singleVote = new SingleVote();

		legislatorRole = new LegislatorRole();
		chamber = new Chamber();
		legislatorRole.setChamber(chamber);

		vote = new Vote();
		voteDetail = "A string";

		singleVote.setLegislatorRole(legislatorRole);
		singleVote.setVote(vote);
		singleVote.setVoteDetail(voteDetail);

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(chamber);
		em.persist(legislatorRole);
		em.persist(vote);
		em.persist(singleVote);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(singleVote);
		em.remove(vote);
		em.remove(legislatorRole);
		em.remove(chamber);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testNewSingleVote() {
		assert (singleVote.getId() != null);
	}

	public void testGetVote() {
		assert (singleVote.getVote().equals(vote));
	}

	public void testGetLegislatorRole() {
		assert (singleVote.getLegislatorRole().equals(legislatorRole));
	}

	public void testGetVoteDetails() {
		assert (singleVote.getVoteDetail().equals(voteDetail));
	}
}