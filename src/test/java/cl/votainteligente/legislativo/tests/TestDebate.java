package cl.votainteligente.legislativo.tests;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.DiscussionType;

public class TestDebate extends TestCase {

	private EntityManager em;
	private Debate debate;
	private Bill bill;
	private Chamber chamber;
	private Date date, entryDate;
	private DiscussionType discussionType;
	private String topic;
	private Timestamp updatedAt;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		debate = new Debate();
		bill = new Bill();
		chamber = new Chamber();
		date = new Date();
		entryDate = new Date();
		updatedAt = new Timestamp(1234);

		bill.setUpdatedAt(updatedAt);
		bill.setEntryDate(entryDate);
		discussionType = new DiscussionType();
		topic = "El primer informe de esta comisión que"
				+ " estudia el envejecimiento precoz de la piel";
		debate.setBill(bill);
		debate.setChamber(chamber);
		debate.setDate(date);
		debate.setDiscussionType(discussionType);
		debate.setTopic(topic);

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(chamber);
		em.persist(bill);
		em.persist(discussionType);
		em.persist(debate);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(debate);
		em.remove(discussionType);
		em.remove(bill);
		em.remove(chamber);
		tr.commit();
		em.clear();
		em.close();
	}

	public void testNewDebate() {
		assertNotNull(debate.getId());
	}

	public void testGetChamber() {
		assertNotNull(debate.getChamber());
		assertEquals(debate.getChamber(), chamber);
	}

	public void testGetDiscussionType() {
		assertNotNull(debate.getDiscussionType());
		assertEquals(debate.getDiscussionType(), discussionType);
	}

	public void testGetBill() {
		assertNotNull(debate.getBill());
		assertEquals(debate.getBill(), bill);
	}

	public void testGetDate() {
		assertNotNull(debate.getDate());
		assertEquals(debate.getDate(), date);
	}

	public void testGetTopic() {
		assertNotNull(debate.getTopic());
		assertEquals(debate.getTopic(), topic);
	}
}
