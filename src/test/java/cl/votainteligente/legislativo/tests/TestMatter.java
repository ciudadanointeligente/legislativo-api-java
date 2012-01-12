package cl.votainteligente.legislativo.tests;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Matter;

public class TestMatter extends TestCase {

	private EntityManager em;
	private Matter matter;
	private String name, superMatter;
	private HashSet<Bill> bills;
	private Bill bill;
	private Chamber chamber;
	private Date entryDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		matter = new Matter();
		name = "Impuestos";
		superMatter = "Hacienda, Economía, Impuesto y Empresas";
		createdAt = new Timestamp(1234);
		updatedAt = new Timestamp(1234);
		bill = new Bill();
		entryDate = new Date();
		bill.setEntryDate(entryDate);
		bill.setUpdatedAt(updatedAt);
		chamber = new Chamber();
		bill.setOriginChamber(chamber);
		bills = new HashSet<Bill>();
		bills.add(bill);
		matter.setName(name);
		matter.setSuperMatter(superMatter);
		matter.setCreatedAt(createdAt);
		matter.setUpdatedAt(updatedAt);
		matter.setBills(bills);

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(chamber);
		em.persist(bill);
		em.persist(matter);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(matter);
		em.remove(bill);
		em.remove(chamber);
		tr.commit();
		em.close();
	}

	public void testNewMatter() {
		assertNotNull(matter.getId());
	}

	public void testGetName() {
		assertNotNull(matter.getName());
		assertEquals(matter.getName(), name);
	}

	public void testGetSuperMatter() {
		assertNotNull(matter.getSuperMatter());
		assertEquals(matter.getSuperMatter(), superMatter);
	}

	public void testGetCreatedAt() {
		assertNotNull(matter.getCreatedAt());
		assertEquals(matter.getCreatedAt(), createdAt);
	}

	public void testGetUpdatedAt() {
		assertNotNull(matter.getUpdatedAt());
		assertEquals(matter.getUpdatedAt(), updatedAt);
	}

	public void testGetBills() {
		assertNotNull(matter.getBills());
		assertEquals(matter.getBills(), bills);
	}
}
