package cl.votainteligente.legislativo.tests;

import java.util.Date;
import java.sql.Timestamp;
import junit.framework.TestCase;
import cl.votainteligente.legislativo.model.*;
import javax.persistence.*;

public class TestPerson extends TestCase {

	private EntityManager em;
	private Person person;
	private Date birthday;
	private Timestamp createdAt, updatedAt;

	protected void setUp() throws Exception {
		super.setUp();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PersistenceLegislativo");
		em = emf.createEntityManager();
		emf.close();
		createdAt = new Timestamp(123214);
		updatedAt = new Timestamp(123214);
		person = new Person();
		birthday = new Date();
		person.setBirthday(birthday);
		person.setFacebookAccount("people/Carlos-Cantero-Ojeda/1098675519");
		person.setTwitterAccount("senadorcantero");
		person.setFirstName("Carlos");
		person.setLastName("Cantero");
		person.setMailAddress("ccantero@senado.cl");
		person.setWebsite("www.cantero.cl");
		person.setParty("INDEPENDIENTE");
		person.setGraduateEducation("No tiene");
		person.setUniversityEducation("Geografía, Universidad Católica del Norte de Antof...");
		person.setStatementOfHeritage("No ha hecho");
		person.setStatementOfInterest("No ha hecho");
		person.setProfession("Geógrafo");
		person.setCreatedAt(createdAt);
		person.setUpdatedAt(updatedAt);
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.persist(person);
		tr.commit();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		em.remove(person);
		tr.commit();
		em.close();
	}

	public void testEntityManagerNotNull() {
		em.getClass();
		assert (em != null);
	}

	public void testNewPerson() {
		assert (person.getId() != null);
	}

	public void testGetBirthday() {
		assert (person.getBirthday().equals(birthday));
	}

	public void testGetFacebookAccount() {
		assert (person.getFacebookAccount()
				.equals("people/Carlos-Cantero-Ojeda/1098675519"));
	}

	public void testGetTwitterAccount() {
		assert (person.getTwitterAccount().equals("senadorcantero"));
	}

	public void testGetFirstName() {
		assert (person.getFirstName().equals("Carlos"));
	}

	public void testGetLastName() {
		assert (person.getLastName().equals("Cantero"));
	}

	public void testGetMailAddress() {
		assert (person.getMailAddress().equals("ccantero@senado.cl"));
	}

	public void testGetWebsite() {
		assert (person.getWebsite().equals("www.cantero.cl"));
	}

	public void testGetRoles() {
		// TODO: needs to be updated
		return;
	}

	public void testGetParty() {
		assert (person.getParty().equals("INDEPENDIENTE"));
	}

	public void testGetGraduateEducation() {
		assert (person.getGraduateEducation().equals("No tiene"));
	}

	public void testGetUniversityEducation() {
		assert (person.getUniversityEducation()
				.equals("Geografía, Universidad Católica del Norte de Antof..."));
	}

	public void testGetStatementOfHeritage() {
		assert (person.getStatementOfHeritage().equals("No ha hecho"));
	}

	public void testGetStatementOfInterest() {
		assert (person.getStatementOfInterest().equals("No ha hecho"));
	}

	public void testGetProfession() {
		assert (person.getProfession().equals("Geógrafo"));
	}

	public void testGetCreatedAt() {
		assert (person.getCreatedAt().equals(createdAt));
	}

	public void testGetUpdatedAt() {
		assert (person.getUpdatedAt().equals(updatedAt));
	}
}