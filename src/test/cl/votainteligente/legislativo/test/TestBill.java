package cl.votainteligente.legislativo.test;

import java.util.Date;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.*;
import javax.persistence.*;

public class TestBill extends TestCase {

	private EntityManager em;
	private Bill bill;
	private Chamber originChamber;
	private Matter matter;
	private Date createdAt, entryDate, publicationDate, updatedAt;
	private Legislator president, firstVicePresident;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		// Bill object
		bill = new Bill();
		bill.setBCNLawId(new Long(123));
		bill.setBCNLawURL("law");
		bill.setBulletinNumber("123");
		bill.setCreatedAt(createdAt = new Date());
		bill.setDecree(new Long(123));
		bill.setEntryDate(entryDate = new Date());
		bill.setInitiative("initiative");
		bill.setPublicationDate(publicationDate = new Date());
		bill.setPublished(true);
		bill.setSILIndicationsId(new Long(123));
		bill.setSILOficiosId(new Long(123));
		bill.setSILProcessingsId(new Long(123));
		bill.setSILUrgenciesId(new Long(123));
		bill.setStage("stage");
		bill.setSubStage("subStage");
		bill.setSummary("summary");
		bill.setTitle("title");
		bill.setUpdatedAt(updatedAt = new Date());
		bill.setUrgency("urgency");

		// Matter object
		matter = new Matter();
		matter.setName("matter");
		matter.setSuperMatter("superMatter");
		matter.setCreatedAt(new Date());
		matter.setUpdatedAt(new Date());
		bill.setMatter(matter);

		// Chamber object: requires president and first vice president
		president = new Legislator();
		firstVicePresident = new Legislator();
		originChamber = new Chamber();
		originChamber.setName("Senado");
		originChamber.setPresident(president);
		originChamber.setFirst_vice_president(firstVicePresident);
		president.setChamber(originChamber);
		firstVicePresident.setChamber(originChamber);
		bill.setOriginChamber(originChamber);

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(matter);
		em.persist(originChamber);
		em.persist(president);
		em.persist(firstVicePresident);
		em.persist(bill);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(bill);
		em.remove(originChamber);
		em.remove(matter);
		em.remove(president);
		em.remove(firstVicePresident);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testNewBill() {
		assert (bill.getId() != null);
	}

	public void testGetBulletinNumber() {
		assert (bill.getBulletinNumber().equals("123"));
	}

	public void testGetTitle() {
		assert (bill.getTitle().equals("title"));
	}

	public void testGetEntryDate() {
		assert (bill.getEntryDate().equals(entryDate));
	}

	public void testIsPublished() {
		assert (bill.isPublished());
	}

	public void testGetPublicationDate() {
		assert (bill.getPublicationDate().equals(publicationDate));
	}

	public void testGetBCNLawURL() {
		assert (bill.getBCNLawURL().equals("law"));
	}

	public void testGetInitiative() {
		assert (bill.getInitiative().equals("initiative"));
	}

	public void testGetOriginChamber() {
		assert (bill.getOriginChamber().equals(originChamber));
	}

	public void testGetUrgency() {
		assert (bill.getUrgency().equals("urgency"));
	}

	public void testGetStage() {
		assert (bill.getStage().equals("stage"));
	}

	public void testGetSubStage() {
		assert (bill.getSubStage().equals("subStage"));
	}

	public void testGetMatter() {
		assert (bill.getMatter().equals(matter));
	}

	public void testGetDecree() {
		assert (bill.getDecree().equals(new Long(123)));
	}

	public void testGetSummary() {
		assert (bill.getSummary().equals("summary"));
	}

	public void testGetSILProcessingsId() {
		assert (bill.getSILProcessingsId().equals(new Long(123)));
	}

	public void testGetSILOficiosId() {
		assert (bill.getSILOficiosId().equals(new Long(123)));
	}

	public void testGetSILIndicationsId() {
		assert (bill.getSILIndicationsId().equals(new Long(123)));
	}

	public void testGetSILUrgenciesId() {
		assert (bill.getSILUrgenciesId().equals(new Long(123)));
	}

	public void testGetCreatedAt() {
		assert (bill.getCreatedAt().equals(createdAt));
	}

	public void testGetUpdatedAt() {
		assert (bill.getUpdatedAt().equals(updatedAt));
	}
}