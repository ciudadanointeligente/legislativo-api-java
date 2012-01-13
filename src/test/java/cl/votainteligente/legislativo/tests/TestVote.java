package cl.votainteligente.legislativo.tests;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.SessionChamber;
import cl.votainteligente.legislativo.model.SingleVote;
import cl.votainteligente.legislativo.model.Vote;

public class TestVote extends TestCase {

	private EntityManager em;
	private Vote vote;
	private Bill bill;
	private Set<SingleVote> votes;
	private Chamber chamber;
	private int absentVotes, abstentionVotes, matchingVotes, noVotes, yesVotes;
	private Timestamp createdAt, updatedAt;
	private Long result;
	private SessionChamber session;
	private String quorum;
	private Date entryDate;
	private SingleVote aVote;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = LocalEntityManager.factory();
		em = emf.createEntityManager();
		emf.close();

		absentVotes = 12;
		abstentionVotes = 2;
		yesVotes = 4;
		noVotes = 43;
		matchingVotes = 0;
		result = 1l;
		votes = new HashSet<SingleVote>();
		aVote = new SingleVote();
		votes.add(aVote);

		createdAt = new Timestamp(123214);
		updatedAt = new Timestamp(123214);

		bill = new Bill();
		entryDate = new Date();
		bill.setEntryDate(entryDate);
		bill.setUpdatedAt(updatedAt);

		quorum = "No quorum";
		session = new SessionChamber();
		chamber = new Chamber();
		session.setChamber(chamber);

		vote = new Vote();
		vote.setAbsentVotes(absentVotes);
		vote.setAbstentionVotes(abstentionVotes);
		vote.setBill(bill);
		vote.setCreatedAt(createdAt);
		vote.setMatchingVotes(matchingVotes);
		vote.setNoVotes(noVotes);
		vote.setQuorum(quorum);
		vote.setResult(result);
		vote.setSession(session);
		vote.setUpdatedAt(updatedAt);
		vote.setYesVotes(yesVotes);
		vote.setVotes(votes);

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(chamber);
		em.persist(session);
		em.persist(bill);
		em.persist(aVote);
		em.persist(vote);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(vote);
		em.remove(aVote);
		em.remove(bill);
		em.remove(session);
		em.remove(chamber);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testNewVote() {
		assert (vote.getId() != null);
	}

	public void testGetBill() {
		assert (vote.getBill().equals(bill));
	}

	public void testGetSession() {
		assert (vote.getSession().equals(session));
	}

	public void testGetVotes() {
		assert (vote.getVotes().equals(votes));
	}

	public void testGetAbsentVotes() {
		assert (vote.getAbsentVotes() == absentVotes);
	}

	public void testGetAbstentionVotes() {
		assert (vote.getAbstentionVotes() == abstentionVotes);
	}

	public void testGetNoVotes() {
		assert (vote.getNoVotes() == noVotes);
	}

	public void testGetYesVotes() {
		assert (vote.getYesVotes() == yesVotes);
	}

	public void testGetMatchingVotes() {
		assert (vote.getMatchingVotes() == matchingVotes);
	}

	public void testGetCreatedAt() {
		assert (vote.getCreatedAt().equals(createdAt));
	}

	public void testGetUpdatedAt() {
		assert (vote.getUpdatedAt().equals(updatedAt));
	}

	public void testGetQuorum() {
		assert (vote.getQuorum().equals(quorum));
	}

	public void testGetResult() {
		assert (vote.getResult() == result);
	}

}