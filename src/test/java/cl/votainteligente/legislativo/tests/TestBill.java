package cl.votainteligente.legislativo.tests;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.*;
import javax.persistence.*;

public class TestBill extends TestCase {

	private EntityManager em;
	private Bill bill;
	private Chamber originChamber;
	private Matter matter;
	private Date createdAt, entryDate, publicationDate, updatedAt;
	private StageDescription stageDescription1, stageDescription2;
	private Stage stage1, stage2;

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

		HashSet<Stage> stages = new HashSet<Stage>();
		stage1 = new Stage();
		stageDescription1 = new StageDescription();
		stageDescription1.setDescription("Description1");
		stage1.setStageDescription(stageDescription1);
		stage1.setEntryDate(new Date());
		stage1.setEndDate(new Date());

		stage2 = new Stage();
		stageDescription2 = new StageDescription();
		stageDescription2.setDescription("Description2");
		stage2.setStageDescription(stageDescription2);
		stage2.setEntryDate(new Date());
		stage2.setEndDate(new Date());

		stages.add(stage1);
		stages.add(stage2);

		bill.setStages(stages);
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
		originChamber = new Chamber();
		bill.setOriginChamber(originChamber);
		EntityTransaction tr = em.getTransaction();
		tr.begin();

		em.persist(stageDescription1);
		em.persist(stageDescription2);

		em.persist(stage1);
		em.persist(stage2);

		em.persist(originChamber);
		em.persist(matter);
		em.persist(bill);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(bill);
		em.remove(matter);
		em.remove(originChamber);
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
		Set<Stage> stages = bill.getStages();
		assert (stages.contains(stage1));
		assert (stages.contains(stage2));
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