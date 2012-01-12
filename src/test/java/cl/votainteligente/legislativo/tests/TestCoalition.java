package cl.votainteligente.legislativo.tests;

import java.net.URL;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Coalition;

public class TestCoalition extends TestCase {
	private EntityManager em;
	private Coalition coalition;
	private String address, name;
	private URL web;
	private Date foundationDate;
	private Date dissolutionDate;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		coalition = new Coalition();
		address = "Santo Domingo 1828. Santiago, Santiago, Chile";
		name = "Concertación de Partidos por la Democracia";
		web = new URL("http://www.concertacion.cl");
		foundationDate = new Date();
		dissolutionDate = new Date();
		coalition.setName(name);
		coalition.setAddress(address);
		coalition.setWeb(web);
		coalition.setFoundationDate(foundationDate);
		coalition.setDissolutionDate(dissolutionDate);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(coalition);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(coalition);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assertNotNull(em);
	}

	public void testGetId() {
		assertNotNull(coalition.getId());
	}

	public void testGetAddress() {
		assertNotNull(coalition.getAddress());
		assertEquals(coalition.getAddress(), address);
	}

	public void testGetName() {
		assertNotNull(coalition.getName());
		assertEquals(coalition.getName(), name);
	}

	public void testGetWeb() {
		assertNotNull(coalition.getWeb());
		assertEquals(coalition.getWeb(), web);
	}

	public void testGetFoundationDate() {
		assertNotNull(coalition.getFoundationDate());
		assertEquals(coalition.getFoundationDate(), foundationDate);
	}

	public void testGetDissolutionDate() {
		assertNotNull(coalition.getDissolutionDate());
		assertEquals(coalition.getDissolutionDate(), dissolutionDate);
	}
}