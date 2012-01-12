package cl.votainteligente.legislativo.tests;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.model.SessionCommission;

public class TestCommission extends TestCase {
	private EntityManager em;
	private Commission commission;
	private Set<SessionCommission> sessions;
	private SessionCommission session;
	private Chamber chamber;
	private String executiveLawyer, secretaryLawyer, assistantLawyer, form,
			phoneNumber;
	private CommissionType commissionType;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();

		commission = new Commission();
		executiveLawyer = "Sergio Malagamba Stiglich";
		secretaryLawyer = "Leonardo Lueiza Ureta";
		assistantLawyer = "Paula Müller Morales";
		form = "http://camara.cl/camara/formulario_contacto.aspx";
		phoneNumber = "(56+32) 250 5521";
		chamber = new Chamber();
		session = new SessionCommission();
		sessions = new HashSet<SessionCommission>();
		sessions.add(session);
		commissionType = new CommissionType();
		commission.setAssistantLawyer(assistantLawyer);
		commission.setChamber(chamber);
		commission.setExecutiveLawyer(executiveLawyer);
		commission.setSecretaryLawyer(secretaryLawyer);
		commission.setPhoneNumber(phoneNumber);
		commission.setForm(form);
		commission.setSessions(sessions);
		commission.setCommissionType(commissionType);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(commissionType);
		em.persist(chamber);
		em.persist(session);
		em.persist(commission);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(commission);
		em.remove(session);
		em.remove(chamber);
		em.remove(commissionType);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assertNotNull(em);
	}

	public void testGetId() {
		assertNotNull(commission.getId());
	}

	public void testGetAssistantLawyer() {
		assertNotNull(commission.getAssistantLawyer());
		assertEquals(commission.getAssistantLawyer(), assistantLawyer);
	}

	public void testGetSecretaryLawyer() {
		assertNotNull(commission.getSecretaryLawyer());
		assertEquals(commission.getSecretaryLawyer(), secretaryLawyer);
	}

	public void testGetExecutiveLawyer() {
		assertNotNull(commission.getExecutiveLawyer());
		assertEquals(commission.getExecutiveLawyer(), executiveLawyer);
	}

	public void testGetForm() {
		assertNotNull(commission.getForm());
		assertEquals(commission.getForm(), form);
	}

	public void testGetPhoneNumber() {
		assertNotNull(commission.getPhoneNumber());
		assertEquals(commission.getPhoneNumber(), phoneNumber);
	}

	public void testGetChamber() {
		assertNotNull(commission.getChamber());
		assertEquals(commission.getChamber(), chamber);
	}

	public void testGetSessions() {
		assertNotNull(commission.getSessions());
		assertEquals(commission.getSessions(), sessions);
	}

	public void testGetCommissionType() {
		assertNotNull(commission.getCommissionType());
		assertEquals(commission.getCommissionType(), commissionType);
	}
}